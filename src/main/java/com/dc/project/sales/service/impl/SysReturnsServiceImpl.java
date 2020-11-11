package com.dc.project.sales.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.lang.annotation.DataScope;
import com.dc.common.constant.CustomConstant;
import com.dc.common.constant.SalesConstant;
import com.dc.common.exception.ServiceException;
import com.dc.common.utils.*;
import com.dc.project.finance.entity.SysReceivable;
import com.dc.project.finance.service.ISysReceivableService;
import com.dc.project.sales.dao.SysReturnsDao;
import com.dc.project.sales.entity.SysOrderSub;
import com.dc.project.sales.entity.SysReturns;
import com.dc.project.sales.entity.SysReturnsSub;
import com.dc.project.sales.service.ISysOrderSubService;
import com.dc.project.sales.service.ISysReturnsService;
import com.dc.project.sales.service.ISysReturnsSubService;
import com.dc.project.warehouse.entity.SysRepertory;
import com.dc.project.warehouse.service.ISysRepertoryService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 销售退货主表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-10-11
 */
@Service
public class SysReturnsServiceImpl extends ServiceImpl<SysReturnsDao, SysReturns> implements ISysReturnsService {
    @Autowired
    private ISysReturnsSubService returnsSubService;
    @Autowired
    private ISysRepertoryService repertoryService;
    @Autowired
    private ISysOrderSubService orderSubService;
    @Autowired
    private ISysReceivableService receivableService;

    @DataScope(userAlias = "sr", userColumn = "create_id")
    @Override
    public IPage<SysReturns> page(Page<SysReturns> page, SysReturns sysReturns) {
        return baseMapper.page(page, sysReturns);
    }

    @Override
    public SysReturns get(Integer id) {
        return baseMapper.get(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Integer id) {
        SysReturns returns = this.getById(id);
        if (null == returns) throw new ServiceException("已删除");
        SalesConstant.verifyDeleteStatus(returns.getStatus());
        if (!this.removeById(id))
            throw new ServiceException(String.format("%s,删除失败", returns.getReturnsNum()));
        if (!returnsSubService.remove(new QueryWrapper<SysReturnsSub>().eq("returns_id", id)))
            throw new ServiceException("删除失败");
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveAndUpdate(Map<String, Object> formMap) throws InvocationTargetException, IllegalAccessException {
        Object clienteleForm = formMap.get("clientele");
        Object materielListForm = formMap.get("materielList");
        Object delSubIdsForm = formMap.get("delSubIds");
        if (null == clienteleForm || null == materielListForm)
            throw new ServiceException("保存失败");
        SysReturns sysReturns = new SysReturns();
        BeanUtil.register();
        BeanUtils.populate(sysReturns, ObjectMapperUtil.toObject(clienteleForm.toString(), Map.class));
        if (null == sysReturns.getReturnsId()) {
            sysReturns.setReturnsNum(CodeUtil.getCode(SalesConstant.SALES_RETURNS_NO));
            sysReturns.setStatus(SalesConstant.SAVE);
            sysReturns.setAuditStatus(SalesConstant.SAVE);
            if (!this.save(sysReturns)) throw new ServiceException("保存失败");
        } else {
            if (!this.updateById(sysReturns)) throw new ServiceException("修改失败");
        }

        List<Map<String, Object>> subList = ObjectMapperUtil.toObject(materielListForm.toString(), List.class);
        if (subList.isEmpty()) throw new ServiceException("没有退货的产品");
        for (Map<String, Object> map : subList) {
            SysReturnsSub sub = new SysReturnsSub();
            BeanUtils.populate(sub, map);
            if (null != sub.getReturnsNum() && 0 != sub.getReturnsNum()) {
                if (null == sub.getSubId()) {
                    sub.setReturnsId(sysReturns.getReturnsId());
                    if (!returnsSubService.save(sub)) {
                        throw new ServiceException("保存失败");
                    }
                } else {
                    UpdateWrapper<SysReturnsSub> uw = new UpdateWrapper<>();
                    uw.set(null != sub.getReturnsNum(), "returns_num", sub.getReturnsNum());
                    uw.set(null != sub.getRealityNum(), "reality_num", sub.getRealityNum());
                    uw.eq("sub_id", sub.getSubId());
                    if (!returnsSubService.update(uw)) {
                        throw new ServiceException("修改失败");
                    }
                }
            }
        }
        if (null != delSubIdsForm) {
            List<Long> delSubIds = ObjectMapperUtil.toObject(delSubIdsForm.toString(), List.class);
            if (delSubIds.isEmpty()) {
                return sysReturns.getReturnsNum();
            }
            if (!returnsSubService.removeByIds(delSubIds)) {
                throw new ServiceException();
            }
        }
        return sysReturns.getReturnsNum();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean submit(Integer[] ids, String status) {
        if (ids.length == 0 || StringUtils.isEmpty(status)) throw new ServiceException("操作失败");
        List<SysReturns> list = this.list(new QueryWrapper<SysReturns>()
                .select("returns_id,returns_num,status").in("returns_id", Arrays.asList(ids)));
        if (list.isEmpty()) throw new ServiceException("操作失败");
        List<Integer> idList = new ArrayList<>();
        for (SysReturns sysReturns : list) {
            SalesConstant.verifySubmitStatus(status, sysReturns.getReturnsNum(), sysReturns.getStatus());
            idList.add(sysReturns.getReturnsId());
        }
        UpdateWrapper<SysReturns> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", status).in("returns_id", idList);
        return this.update(updateWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean audit(SysReturns sysReturns) {
        SysReturns returns = this.getById(sysReturns.getReturnsId());
        if (null == returns) {
            throw new ServiceException("审核失败，退货单不存在");
        }
        SalesConstant.verifyAuditStatus(sysReturns.getStatus(), returns.getStatus());
        if (SalesConstant.NO_AUDIT.equals(sysReturns.getStatus()) && SalesConstant.AUDIT.equals(returns.getAuditStatus())) {
            throw new ServiceException("反审核失败，生成的退货入库单已审核");
        }
        //  生成应付款单、退货数量回写到订单
        if (SalesConstant.AUDIT.equals(sysReturns.getStatus())) {
            insertReceivable(returns);
        } else {
            delReceivable(returns.getReturnsNum());
        }
        updateOrderSub(sysReturns);
        sysReturns.setAuditTime(new Date());
        sysReturns.setAuditBy(UserSecurityUtil.getUsername());
        if (!this.updateById(sysReturns)) {
            throw new ServiceException();
        }
        return true;
    }

    private void updateOrderSub(SysReturns returns) {
        List<SysReturnsSub> subList = returnsSubService.list(new QueryWrapper<SysReturnsSub>().eq("returns_id", returns.getReturnsId()));
        for (SysReturnsSub sub : subList) {
            SysOrderSub one = orderSubService.getOne(new QueryWrapper<SysOrderSub>()
                    .select("sub_id,number,has_signback_num,has_return_num")
                    .eq("sub_id", sub.getOrderSubId()));
            if (SalesConstant.AUDIT.equals(returns.getStatus())) {
                one.setHasSignbackNum(one.getHasSignbackNum() - sub.getReturnsNum());
                one.setHasReturnNum(one.getHasReturnNum() + sub.getReturnsNum());
            } else {
                one.setHasSignbackNum(one.getHasSignbackNum() + sub.getReturnsNum());
                one.setHasReturnNum(one.getHasReturnNum() - sub.getReturnsNum());
            }
            if (one.getHasReturnNum() > one.getNumber()) {
                throw new ServiceException(String.format("%s %s %s 累计退货数量超过产品订购数量"));
            } else if (one.getHasReturnNum() < 0) {
                throw new ServiceException("数值校验异常");
            }
            if (!orderSubService.updateById(one)) {
                throw new ServiceException();
            }
        }

    }

    private void insertReceivable(SysReturns returns) {
        // 校验金额，退货金额《总金额-订单子表累计签收发货退货金额
//        List<SysOrderSub> orderSubList = orderSubService.list(new QueryWrapper<SysOrderSub>().eq("order_id", returns.getOrderId()));
//        BigDecimal totalPrice = BigDecimalUtil.ZERO;
//        BigDecimal signTotalPrice = BigDecimalUtil.ZERO;
//        for (SysOrderSub sub : orderSubList) {
//            totalPrice = BigDecimalUtil.add(totalPrice, BigDecimalUtil.mul(sub.getPrice(), sub.getNumber()));
//            int number = sub.getHasShipmentNum() + sub.getHasOutboundNum() + sub.getHasSignbackNum() + sub.getHasReturnNum();
//            signTotalPrice = BigDecimalUtil.add(signTotalPrice, BigDecimalUtil.mul(sub.getPrice(), number));
//        }
//        if (BigDecimalUtil.compareTo(returns.getTotalPrice(), BigDecimalUtil.sub(totalPrice, signTotalPrice)) > 0) {
//            throw new ServiceException(String.format("%本次退货金额超过订单可退货金额", returns.getReturnsNum()));
//        }
        SysReceivable receivable = new SysReceivable();
        BeanUtil.copyBeanProp(receivable, returns);
        receivable.setAuditBy(null);
        receivable.setAuditTime(null);
        receivable.setStatus(SalesConstant.SUBMIT);
        receivable.setFinanceTime(new Date());
        receivable.setSourceType(SalesConstant.RECEIVABLE_TYPE_RETURNS);

        receivable.setSignbackNum(returns.getReturnsNum());
        receivable.setSignbackTime(returns.getReturnsTime());
        receivable.setTotalPrice(BigDecimalUtil.sub(BigDecimalUtil.ZERO, returns.getTotalPrice()));
        receivable.setVerificaPrice(receivable.getTotalPrice());
        receivable.setReceivableNum(CodeUtil.getCode(SalesConstant.FINANCE_RECEIVABLE_NO));
        if (!receivableService.save(receivable)) {
            throw new ServiceException("生成应收款异常");
        }
    }

    private void delReceivable(String returnsNum) {
        QueryWrapper<SysReceivable> queryWrapper = new QueryWrapper<SysReceivable>().eq("signback_num", returnsNum);
        SysReceivable one = receivableService.getOne(queryWrapper);
        if (null == one) {
            log.error(String.format("%s此退货单的应收款单不存在"));
            return;
        }
        if (SalesConstant.AUDIT.equals(one.getStatus())) {
            throw new ServiceException(String.format("生成应收款单%s已审核", one.getReceivableNum()));
        }
        if (!receivableService.removeById(one.getReceivableId())) {
            throw new ServiceException("应收款单删除失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean auditStorage(SysReturns sysReturns) {
        SysReturns returns = this.getById(sysReturns.getReturnsId());
        SalesConstant.verifyAuditStatus(sysReturns.getAuditStatus(), returns.getAuditStatus());
        sysReturns.setAuditTime(new Date());
        sysReturns.setAuditBy(UserSecurityUtil.getUsername());
        List<SysReturnsSub> subList = returnsSubService.list(new QueryWrapper<SysReturnsSub>().eq("returns_id", sysReturns.getReturnsId()));
        if (SalesConstant.AUDIT.equals(sysReturns.getAuditStatus())) {
            sysReturns.setStorageStatus(CustomConstant.YES_STATUS);
            //添加库存
            repertoryService.saveAndUpdate(this.transformRepertory(returns, subList, SalesConstant.AUDIT));
        } else {
            sysReturns.setStorageStatus(CustomConstant.NO_STATUS);
            //减少库存
            repertoryService.saveAndUpdate(this.transformRepertory(returns, subList, SalesConstant.NO_AUDIT));
        }
        return this.updateById(sysReturns);
    }

    @Override
    public boolean submitStorage(Integer[] ids, String status) {
        if (ids.length == 0 || StringUtils.isEmpty(status)) throw new ServiceException("操作失败");
        List<SysReturns> list = this.list(new QueryWrapper<SysReturns>()
                .select("returns_id,returns_num,audit_status").in("returns_id", Arrays.asList(ids)));
        if (list.isEmpty()) throw new ServiceException("操作失败");
        List<Integer> idList = new ArrayList<>();
        for (SysReturns sysReturns : list) {
            SalesConstant.verifySubmitStatus(status, sysReturns.getReturnsNum(), sysReturns.getAuditStatus());
            idList.add(sysReturns.getReturnsId());
        }
        UpdateWrapper<SysReturns> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("audit_status", status).in("returns_id", idList);
        return this.update(updateWrapper);
    }

    private List<SysRepertory> transformRepertory(SysReturns sysReturns, List<SysReturnsSub> subList, String audit) {
        if (subList.isEmpty()) {
            throw new ServiceException("未找到产品信息");
        }
        List<SysRepertory> resultList = new ArrayList<>();
        for (SysReturnsSub sub : subList) {
            SysRepertory repertory = new SysRepertory();
            BeanUtil.copyBeanProp(repertory, sub);
            repertory.setWarehouseId(sysReturns.getWarehouseId());
            repertory.setWarehouseNum(sysReturns.getWarehouseNum());
            repertory.setWarehouseName(sysReturns.getWarehouseName());
            if (null == repertory.getMaterielId() || null == repertory.getWarehouseId() || StringUtils.isEmpty(repertory.getModelName()))
                throw new ServiceException("系统异常,缺少参数");
            SysRepertory one = repertoryService.getSysRepertory(repertory);
            if (SalesConstant.AUDIT.equals(audit)) {
                if (one == null) {
                    resultList.add(repertory);
                } else {
                    int number = one.getNumber() + sub.getRealityNum();
                    one.setNumber(number);
                    one.setTotalPrice(BigDecimalUtil.mul(one.getPrice(), number));
                    resultList.add(one);
                }
            } else {
                if (null == one) {
                    throw new ServiceException(String.format("反审核失败，%s %s 该产品不存在",
                            repertory.getMaterielName(), repertory.getModelName()));
                }
                int number = one.getNumber() - sub.getRealityNum();
                if (number < 0)
                    throw new ServiceException("反审核失败，现存数量小于扣减数量");
                one.setNumber(number);
                one.setTotalPrice(BigDecimalUtil.mul(one.getPrice(), number));
                resultList.add(one);
            }
        }
        return resultList;
    }

}
