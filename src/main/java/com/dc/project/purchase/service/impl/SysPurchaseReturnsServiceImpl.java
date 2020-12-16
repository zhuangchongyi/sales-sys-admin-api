package com.dc.project.purchase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.constant.SalesConstant;
import com.dc.common.exception.ServiceException;
import com.dc.common.utils.BeanUtil;
import com.dc.common.utils.BigDecimalUtil;
import com.dc.common.utils.CodeUtil;
import com.dc.common.utils.UserSecurityUtil;
import com.dc.project.purchase.dao.SysPurchaseReturnsDao;
import com.dc.project.purchase.entity.SysPurchaseOrderSub;
import com.dc.project.purchase.entity.SysPurchaseReturns;
import com.dc.project.purchase.entity.SysPurchaseReturnsSub;
import com.dc.project.purchase.service.ISysPurchaseOrderService;
import com.dc.project.purchase.service.ISysPurchaseOrderSubService;
import com.dc.project.purchase.service.ISysPurchaseReturnsService;
import com.dc.project.purchase.service.ISysPurchaseReturnsSubService;
import com.dc.common.vo.CommonVo;
import com.dc.project.warehouse.entity.SysRepertory;
import com.dc.project.warehouse.service.ISysRepertoryService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 采购退货主表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-12-10
 */
@Service
public class SysPurchaseReturnsServiceImpl extends ServiceImpl<SysPurchaseReturnsDao, SysPurchaseReturns> implements ISysPurchaseReturnsService {
    @Autowired
    private ISysPurchaseReturnsSubService returnsSubService;
    @Autowired
    private ISysRepertoryService repertoryService;
    @Autowired
    private ISysPurchaseOrderService orderService;
    @Autowired
    private ISysPurchaseOrderSubService orderSubService;

    @Override
    public IPage<SysPurchaseReturns> page(Page<SysPurchaseReturns> page, SysPurchaseReturns returns) {
        QueryWrapper<SysPurchaseReturns> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(StringUtils.isNotEmpty(returns.getReturnsNum()), SysPurchaseReturns::getReturnsNum, returns.getReturnsNum())
                .like(StringUtils.isNotEmpty(returns.getOrderNum()), SysPurchaseReturns::getOrderNum, returns.getOrderNum())
                .like(StringUtils.isNotEmpty(returns.getSupplierNum()), SysPurchaseReturns::getSupplierNum, returns.getOrderNum())
                .like(StringUtils.isNotEmpty(returns.getSupplierName()), SysPurchaseReturns::getSupplierName, returns.getSupplierName())
                .eq(StringUtils.isNotEmpty(returns.getStatus()), SysPurchaseReturns::getStatus, returns.getStatus())
                .orderByDesc(SysPurchaseReturns::getReturnsId);
        return this.page(page, queryWrapper);
    }

    @Override
    public SysPurchaseReturns get(Integer id) {
        return this.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String add(CommonVo<SysPurchaseReturns, SysPurchaseReturnsSub> vo) {
        SysPurchaseReturns returns = vo.getHeader();
        List<SysPurchaseReturnsSub> subs = vo.getBodys();
        String returnsNum = CodeUtil.getCode(SalesConstant.SALES_RETURNS_NO);
        returns.setReturnsNum(returnsNum);
        returns.setStatus(SalesConstant.SAVE);
        if (!this.save(returns)) {
            throw new ServiceException("新增失败");
        }
        for (SysPurchaseReturnsSub sub : subs) {
            sub.setReturnsId(returns.getReturnsId());
        }
        if (!returnsSubService.saveBatch(subs)) {
            throw new ServiceException("产品保存失败");
        }
        return returnsNum;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean edit(CommonVo<SysPurchaseReturns, SysPurchaseReturnsSub> vo) {
        SysPurchaseReturns returns = vo.getHeader();
        List<SysPurchaseReturnsSub> subs = vo.getBodys();
        if (!this.updateById(returns)) {
            throw new ServiceException("修改失败");
        }
        for (SysPurchaseReturnsSub sub : subs) {
            if (ObjectUtils.isNotEmpty(sub.getReturnsSubId()) && returnsSubService.updateById(sub)) {
                continue;
            } else {
                sub.setReturnsId(returns.getReturnsId());
                if (returnsSubService.save(sub)) {
                    continue;
                }
            }
            throw new ServiceException("产品修改失败");
        }

        List<Long> delSubIds = vo.getDelSubIds();
        if (ObjectUtils.isNotEmpty(delSubIds) && !returnsSubService.removeByIds(delSubIds)) {
            throw new ServiceException("修改失败");
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Integer id) {
        SysPurchaseReturns one = this.getById(id);
        if (one == null) {
            throw new ServiceException("已删除");
        }
        SalesConstant.verifyDeleteStatus(one.getStatus());
        QueryWrapper<SysPurchaseReturnsSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysPurchaseReturnsSub::getReturnsId, id);
        if (returnsSubService.remove(queryWrapper) && this.removeById(id)) {
            return true;
        }
        throw new ServiceException("删除失败");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean submit(CommonVo<SysPurchaseReturns, SysPurchaseReturnsSub> vo) {
        List<Integer> ids = vo.getIds();
        String status = vo.getStatus();
        List<SysPurchaseReturns> orders = this.listByIds(ids);
        List<Integer> resIds = new ArrayList<>();
        for (SysPurchaseReturns order : orders) {
            SalesConstant.verifySubmitStatus(status, order.getReturnsNum(), order.getStatus());
            resIds.add(order.getReturnsId());
        }
        UpdateWrapper<SysPurchaseReturns> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .set(SysPurchaseReturns::getStatus, status)
                .in(SysPurchaseReturns::getReturnsId, resIds);
        if (!this.update(updateWrapper)) {
            throw new ServiceException();
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean audit(CommonVo<SysPurchaseReturns, SysPurchaseReturnsSub> vo) {
        Integer id = vo.getId();
        SysPurchaseReturns one = this.getById(id);
        if (null == one) {
            throw new ServiceException("该单不存在");
        }
        String updateStatus = vo.getStatus();
        SalesConstant.verifyAuditStatus(updateStatus, one.getStatus());
        // TODO: 2020/12/16 生成一条应收款记录

        SysPurchaseReturns returns = new SysPurchaseReturns();
        returns.setReturnsId(id);
        returns.setStatus(updateStatus);
        returns.setOutboundStatus(0);
        returns.setAuditBy(UserSecurityUtil.getNickname());
        returns.setAuditTime(new Date());
        if (SalesConstant.AUDIT.equals(one.getAuditStatus())) {
            throw new ServiceException("反审核失败，该到货单产品已出库");
        }
        if (!this.updateById(returns)) {
            throw new ServiceException();
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean auditOutbound(CommonVo<SysPurchaseReturns, SysPurchaseReturnsSub> vo) {
        Integer id = vo.getId();
        SysPurchaseReturns one = this.getById(id);
        if (null == one || !SalesConstant.AUDIT.equals(one.getStatus())) {
            throw new ServiceException("该单不存在");
        }
        String updateStatus = vo.getStatus();
        SalesConstant.verifyAuditStatus(updateStatus, one.getAuditStatus());
        List<SysPurchaseReturnsSub> returnsSubs = returnsSubService.list(new QueryWrapper<SysPurchaseReturnsSub>().lambda().eq(SysPurchaseReturnsSub::getReturnsId, id));
        if (returnsSubs.isEmpty()) {
            throw new ServiceException();
        }
        // 修改采购订单信息
        this.updatePurchaseOrderSub(one, returnsSubs, updateStatus);
        // 添加或扣减相应产品的库存
        List<SysRepertory> list = this.transformRepertory(one, returnsSubs, updateStatus);
        repertoryService.saveAndUpdate(list);
        SysPurchaseReturns sign = new SysPurchaseReturns();
        sign.setReturnsId(id);
        sign.setAuditStatus(updateStatus);
        sign.setAuditBy(UserSecurityUtil.getNickname());
        sign.setAuditTime(new Date());
        if (SalesConstant.NO_AUDIT.equals(updateStatus)) {
            sign.setOutboundStatus(0);
        } else {
            sign.setOutboundStatus(1);
        }
        if (!this.updateById(sign)) {
            throw new ServiceException();
        }
        return true;
    }

    private List<SysRepertory> transformRepertory(SysPurchaseReturns returns, List<SysPurchaseReturnsSub> returnsSubs, String updateStatus) {
        List<SysRepertory> resultList = new ArrayList<>();
        for (SysPurchaseReturnsSub signSub : returnsSubs) {
            SysRepertory repertory = new SysRepertory();
            BeanUtil.copyBeanProp(repertory, signSub);
            repertory.setWarehouseId(returns.getWarehouseId());
            repertory.setWarehouseNum(returns.getWarehouseNum());
            repertory.setWarehouseName(returns.getWarehouseName());
            if (null == repertory.getMaterielId() || null == repertory.getWarehouseId() || StringUtils.isEmpty(repertory.getModelName())) {
                throw new ServiceException("产品参数异常");
            }
            SysRepertory one = repertoryService.getSysRepertory(repertory);
            Integer outboundNum = signSub.getOutboundNum();
            if (null == one) {
                if (SalesConstant.AUDIT.equals(updateStatus)){
                    throw new ServiceException(String.format("该仓库(%s)未找到产品(%s %s)", returns.getWarehouseNum(), signSub.getMaterielNum(), signSub.getModelName()));
                }else{
                    repertory.setNumber(outboundNum);
                    repertory.setTotalPrice(BigDecimalUtil.mul(signSub.getPrice(), outboundNum));
                    resultList.add(repertory);
                }
            } else {
                if (SalesConstant.AUDIT.equals(updateStatus)) {
                    one.setNumber(one.getNumber() - outboundNum);
                } else {
                    one.setNumber(one.getNumber() + outboundNum);
                }
                one.setTotalPrice(BigDecimalUtil.mul(one.getPrice(), one.getNumber()));
                resultList.add(one);
            }

        }
        return resultList;
    }

    private void updatePurchaseOrderSub(SysPurchaseReturns one, List<SysPurchaseReturnsSub> returnsSubs, String updateStatus) {
        List<SysPurchaseOrderSub> orderSubList = new ArrayList<>();
        for (SysPurchaseReturnsSub returnsSub : returnsSubs) {
            SysPurchaseOrderSub orderSub = orderSubService.getOrderSub(returnsSub.getOrderSubId());
            if (null == orderSub) {
                throw new ServiceException(String.format("操作失败，未找到产品(%s %s %s)", returnsSub.getMaterielName(), returnsSub.getMaterielName(), returnsSub.getModelName()));
            }
            // 退货审核(未出库审核)
            if (SalesConstant.AUDIT.equals(updateStatus)) {
                // 订单子表到货数量
                orderSub.setHasReturnNum(orderSub.getHasReturnNum() + returnsSub.getOutboundNum());
            } else {
                orderSub.setHasReturnNum(orderSub.getHasReturnNum() - returnsSub.getOutboundNum());
            }
            if (orderSub.getNumber() < returnsSub.getOutboundNum()) {
                throw new ServiceException(String.format("产品(%s %s %s)采购出库数量大于产品订购数量", returnsSub.getMaterielName(), returnsSub.getMaterielName(), returnsSub.getModelName()));
            }
            // 加入修改
            orderSubList.add(orderSub);
        }
        // 批量修改采购订单子表
        if (!orderSubService.updateBatchById(orderSubList)) {
            throw new ServiceException("修改订单子表信息失败");
        }
    }
}
