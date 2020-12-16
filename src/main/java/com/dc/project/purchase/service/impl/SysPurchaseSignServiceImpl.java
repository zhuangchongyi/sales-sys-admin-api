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
import com.dc.project.purchase.dao.SysPurchaseSignDao;
import com.dc.project.purchase.entity.SysPurchaseOrderSub;
import com.dc.project.purchase.entity.SysPurchaseSign;
import com.dc.project.purchase.entity.SysPurchaseSignSub;
import com.dc.project.purchase.service.ISysPurchaseOrderService;
import com.dc.project.purchase.service.ISysPurchaseOrderSubService;
import com.dc.project.purchase.service.ISysPurchaseSignService;
import com.dc.project.purchase.service.ISysPurchaseSignSubService;
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
 * 采购到货主表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-12-05
 */
@Service
public class SysPurchaseSignServiceImpl extends ServiceImpl<SysPurchaseSignDao, SysPurchaseSign> implements ISysPurchaseSignService {
    @Autowired
    private ISysPurchaseSignSubService signSubService;
    @Autowired
    private ISysRepertoryService repertoryService;
    @Autowired
    private ISysPurchaseOrderService orderService;
    @Autowired
    private ISysPurchaseOrderSubService orderSubService;


    @Override
    public IPage<SysPurchaseSign> page(Page<SysPurchaseSign> page, SysPurchaseSign purchaseSign) {
        QueryWrapper<SysPurchaseSign> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(StringUtils.isNotEmpty(purchaseSign.getSignNum()), SysPurchaseSign::getSignNum, purchaseSign.getSignNum())
                .like(StringUtils.isNotEmpty(purchaseSign.getOrderNum()), SysPurchaseSign::getOrderNum, purchaseSign.getOrderNum())
                .like(StringUtils.isNotEmpty(purchaseSign.getSupplierNum()), SysPurchaseSign::getSupplierNum, purchaseSign.getOrderNum())
                .like(StringUtils.isNotEmpty(purchaseSign.getSupplierName()), SysPurchaseSign::getSupplierName, purchaseSign.getSupplierName())
                .eq(StringUtils.isNotEmpty(purchaseSign.getStatus()), SysPurchaseSign::getStatus, purchaseSign.getStatus())
                .orderByDesc(SysPurchaseSign::getSignId);
        return this.page(page, queryWrapper);
    }

    @Override
    public SysPurchaseSign get(Integer id) {
        return this.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String add(CommonVo<SysPurchaseSign, SysPurchaseSignSub> vo) {
        SysPurchaseSign sign = vo.getHeader();
        List<SysPurchaseSignSub> subs = vo.getBodys();
        String signNum = CodeUtil.getCode(SalesConstant.SALES_SIGNBACK_NO);
        sign.setSignNum(signNum);
        sign.setStatus(SalesConstant.SAVE);
        if (!this.save(sign)) {
            throw new ServiceException("新增失败");
        }
        for (SysPurchaseSignSub sub : subs) {
            sub.setSignId(sign.getSignId());
        }
        if (!signSubService.saveBatch(subs)) {
            throw new ServiceException("产品保存失败");
        }
        return signNum;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean edit(CommonVo<SysPurchaseSign, SysPurchaseSignSub> vo) {
        SysPurchaseSign sign = vo.getHeader();
        List<SysPurchaseSignSub> subs = vo.getBodys();
        if (!this.updateById(sign)) {
            throw new ServiceException("修改失败");
        }
        for (SysPurchaseSignSub sub : subs) {
            if (ObjectUtils.isNotEmpty(sub.getSignSubId()) && signSubService.updateById(sub)) {
                continue;
            } else {
                sub.setSignId(sign.getSignId());
                if (signSubService.save(sub)) {
                    continue;
                }
            }
            throw new ServiceException("产品修改失败");
        }

        List<Long> delSubIds = vo.getDelSubIds();
        if (ObjectUtils.isNotEmpty(delSubIds) && !signSubService.removeByIds(delSubIds)) {
            throw new ServiceException("修改失败");
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Integer id) {
        SysPurchaseSign one = this.getById(id);
        if (one == null) {
            throw new ServiceException("已删除");
        }
        SalesConstant.verifyDeleteStatus(one.getStatus());
        QueryWrapper<SysPurchaseSignSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysPurchaseSignSub::getSignId, id);
        if (signSubService.remove(queryWrapper) && this.removeById(id)) {
            return true;
        }
        throw new ServiceException("删除失败");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean submit(CommonVo<SysPurchaseSign, SysPurchaseSignSub> vo) {
        List<Integer> ids = vo.getIds();
        String status = vo.getStatus();
        List<SysPurchaseSign> orders = this.listByIds(ids);
        List<Integer> resIds = new ArrayList<>();
        for (SysPurchaseSign order : orders) {
            SalesConstant.verifySubmitStatus(status, order.getSignNum(), order.getStatus());
            resIds.add(order.getSignId());
        }
        UpdateWrapper<SysPurchaseSign> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .set(SysPurchaseSign::getStatus, status)
                .in(SysPurchaseSign::getSignId, resIds);
        if (!this.update(updateWrapper)) {
            throw new ServiceException();
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean audit(CommonVo<SysPurchaseSign, SysPurchaseSignSub> vo) {
        Integer id = vo.getId();
        SysPurchaseSign one = this.getById(id);
        if (null == one) {
            throw new ServiceException("该单不存在");
        }
        String updateStatus = vo.getStatus();
        SalesConstant.verifyAuditStatus(updateStatus, one.getStatus());
        // 修改采购订单信息
        List<SysPurchaseSignSub> signSubs = signSubService.list(new QueryWrapper<SysPurchaseSignSub>().lambda().eq(SysPurchaseSignSub::getSignId, id));
        if (signSubs.isEmpty()) {
            throw new ServiceException();
        }
        // 修改采购订单信息
        this.updatePurchaseOrderSub(one, signSubs, updateStatus);
        // TODO: 2020/12/16 生成一条应付款记录

        SysPurchaseSign sign = new SysPurchaseSign();
        sign.setSignId(id);
        sign.setStatus(updateStatus);
        sign.setStorageStatus(0);
        sign.setAuditBy(UserSecurityUtil.getNickname());
        sign.setAuditTime(new Date());
        if (SalesConstant.AUDIT.equals(one.getAuditStatus())) {
            throw new ServiceException("反审核失败，该到货单产品已入库");
        }
        if (!this.updateById(sign)) {
            throw new ServiceException();
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean auditStorage(CommonVo<SysPurchaseSign, SysPurchaseSignSub> vo) {
        Integer id = vo.getId();
        SysPurchaseSign one = this.getById(id);
        if (null == one || !SalesConstant.AUDIT.equals(one.getStatus())) {
            throw new ServiceException("该单不存在");
        }
        String updateStatus = vo.getStatus();
        SalesConstant.verifyAuditStatus(updateStatus, one.getAuditStatus());
        List<SysPurchaseSignSub> signSubs = signSubService.list(new QueryWrapper<SysPurchaseSignSub>().lambda().eq(SysPurchaseSignSub::getSignId, id));
        if (signSubs.isEmpty()) {
            throw new ServiceException();
        }
        // 修改采购订单信息
        this.updatePurchaseOrderSub(one, signSubs, updateStatus);
        // 添加或扣减相应产品的库存
        List<SysRepertory> list = this.transformRepertory(one, signSubs, updateStatus);
        repertoryService.saveAndUpdate(list);
        SysPurchaseSign sign = new SysPurchaseSign();
        sign.setSignId(id);
        sign.setAuditStatus(updateStatus);
        sign.setAuditBy(UserSecurityUtil.getNickname());
        sign.setAuditTime(new Date());
        if (SalesConstant.NO_AUDIT.equals(updateStatus)) {
            sign.setStorageStatus(0);
        } else {
            sign.setStorageStatus(1);
        }
        if (!this.updateById(sign)) {
            throw new ServiceException();
        }
        return true;
    }

    /**
     * 修改采购订单信息
     *
     * @param sign
     * @param subList
     * @param updateStatus
     */
    private void updatePurchaseOrderSub(SysPurchaseSign sign, List<SysPurchaseSignSub> subList, String updateStatus) {
        List<SysPurchaseOrderSub> orderSubList = new ArrayList<>();
        for (SysPurchaseSignSub signSub : subList) {
            SysPurchaseOrderSub orderSub = orderSubService.getOrderSub(signSub.getOrderSubId());
            if (null == orderSub) {
                throw new ServiceException(String.format("操作失败，未找到产品(%s %s %s)", signSub.getMaterielName(), signSub.getMaterielName(), signSub.getModelName()));
            }
            if (0 == sign.getStorageStatus() && !SalesConstant.AUDIT.equals(sign.getAuditStatus())) {
                // 到货审核(未入库审核)
                if (SalesConstant.AUDIT.equals(updateStatus)) {
                    // 添加订单子表到货数量
                    orderSub.setHasSignNum(orderSub.getHasSignNum() + signSub.getSignNum());
                } else {
                    orderSub.setHasSignNum(orderSub.getHasSignNum() - signSub.getSignNum());
                }
            } else if (SalesConstant.AUDIT.equals(sign.getStatus())) {
                // 到货入库审核
                if (SalesConstant.AUDIT.equals(updateStatus)) {
                    // 添加订单子表到货入库数量
                    orderSub.setHasStorageNum(orderSub.getHasStorageNum() + signSub.getStorageNum());
                    orderSub.setHasSignNum(orderSub.getHasSignNum() - signSub.getSignNum());
                } else {
                    orderSub.setHasStorageNum(orderSub.getHasStorageNum() - signSub.getStorageNum());
                    orderSub.setHasSignNum(orderSub.getHasSignNum() + signSub.getSignNum());
                }
            } else {
                throw new ServiceException();
            }
            if (orderSub.getNumber() < (orderSub.getHasSignNum() + orderSub.getHasStorageNum())) {
                throw new ServiceException(String.format("产品(%s %s %s)采购入库数量大于产品订购数量", signSub.getMaterielName(), signSub.getMaterielName(), signSub.getModelName()));
            }
            // 加入修改
            orderSubList.add(orderSub);
        }
        // 批量修改采购订单子表
        if (!orderSubService.updateBatchById(orderSubList)) {
            throw new ServiceException("修改订单子表信息失败");
        }
    }

    /**
     * 计算仓库库存
     *
     * @param sign
     * @param status
     * @return
     */
    private List<SysRepertory> transformRepertory(SysPurchaseSign sign, List<SysPurchaseSignSub> list, String status) {
        List<SysRepertory> resultList = new ArrayList<>();
        for (SysPurchaseSignSub signSub : list) {
            SysRepertory repertory = new SysRepertory();
            BeanUtil.copyBeanProp(repertory, signSub);
            repertory.setWarehouseId(sign.getWarehouseId());
            repertory.setWarehouseNum(sign.getWarehouseNum());
            repertory.setWarehouseName(sign.getWarehouseName());
            if (null == repertory.getMaterielId() || null == repertory.getWarehouseId() || StringUtils.isEmpty(repertory.getModelName())) {
                throw new ServiceException("产品参数异常");
            }
            SysRepertory one = repertoryService.getSysRepertory(repertory);
            Integer storageNum = signSub.getStorageNum();
            if (null == one) {
                if (SalesConstant.AUDIT.equals(status)) {
                    repertory.setNumber(storageNum);
                    repertory.setTotalPrice(BigDecimalUtil.mul(signSub.getPrice(), storageNum));
                    resultList.add(repertory);
                } else {
                    throw new ServiceException(String.format("该仓库(%s)未找到产品(%s %s)", sign.getWarehouseNum(), signSub.getMaterielNum(), signSub.getModelName()));
                }
            } else {
                if (SalesConstant.AUDIT.equals(status)) {
                    one.setNumber(one.getNumber() + storageNum);
                } else {
                    one.setNumber(one.getNumber() - storageNum);
                }
                one.setTotalPrice(BigDecimalUtil.mul(one.getPrice(), one.getNumber()));
                resultList.add(one);
            }

        }
        return resultList;
    }
}
