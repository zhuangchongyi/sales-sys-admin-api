package com.dc.project.purchase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.constant.SalesConstant;
import com.dc.common.exception.ServiceException;
import com.dc.common.utils.CodeUtil;
import com.dc.common.utils.UserSecurityUtil;
import com.dc.project.purchase.dao.SysPurchaseOrderDao;
import com.dc.project.purchase.entity.SysPurchaseOrder;
import com.dc.project.purchase.entity.SysPurchaseOrderSub;
import com.dc.project.purchase.service.ISysPurchaseOrderService;
import com.dc.project.purchase.service.ISysPurchaseOrderSubService;
import com.dc.common.vo.CommonVo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 采购订单主表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-12-02
 */
@Service
public class SysPurchaseOrderServiceImpl extends ServiceImpl<SysPurchaseOrderDao, SysPurchaseOrder> implements ISysPurchaseOrderService {
    @Autowired
    private ISysPurchaseOrderSubService orderSubService;

    @Override
    public IPage<SysPurchaseOrder> page(Page<SysPurchaseOrder> page, SysPurchaseOrder order) {
        QueryWrapper<SysPurchaseOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(StringUtils.isNotEmpty(order.getOrderNum()), SysPurchaseOrder::getOrderNum, order.getOrderNum())
                .like(StringUtils.isNotEmpty(order.getSupplierNum()), SysPurchaseOrder::getSupplierNum, order.getOrderNum())
                .like(StringUtils.isNotEmpty(order.getSupplierName()), SysPurchaseOrder::getSupplierName, order.getSupplierName())
                .like(StringUtils.isNotEmpty(order.getStatus()), SysPurchaseOrder::getStatus, order.getStatus())
                .orderByDesc(SysPurchaseOrder::getOrderId);
        return this.page(page, queryWrapper);
    }

    @Override
    public IPage<SysPurchaseOrder> list(Page<SysPurchaseOrder> page, SysPurchaseOrder order) {
        return baseMapper.list(page, order);
    }

    @Override
    public SysPurchaseOrder get(Integer id) {
        return this.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String add(CommonVo<SysPurchaseOrder, SysPurchaseOrderSub> vo) {
        SysPurchaseOrder order = vo.getHeader();
        List<SysPurchaseOrderSub> subs = vo.getBodys();
        String orderNum = CodeUtil.getCode(SalesConstant.SALES_ORDER_NO);
        order.setOrderNum(orderNum);
        if (!this.save(order)) {
            throw new ServiceException("新增失败");
        }
        for (SysPurchaseOrderSub sub : subs) {
            sub.setOrderId(order.getOrderId());
        }
        if (!orderSubService.saveBatch(subs)) {
            throw new ServiceException("产品新增失败");
        }
        return orderNum;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean edit(CommonVo<SysPurchaseOrder, SysPurchaseOrderSub> vo) {
        SysPurchaseOrder order = vo.getHeader();
        List<SysPurchaseOrderSub> subs = vo.getBodys();
        if (!this.updateById(order)) {
            throw new ServiceException("修改失败");
        }
        for (SysPurchaseOrderSub sub : subs) {
            if (ObjectUtils.isNotEmpty(sub.getOrderSubId()) && orderSubService.updateById(sub)) {
                continue;
            } else {
                sub.setOrderId(order.getOrderId());
                if (orderSubService.save(sub)) {
                    continue;
                }
            }
            throw new ServiceException("产品修改失败");
        }

        List<Long> delSubIds = vo.getDelSubIds();
        if (ObjectUtils.isNotEmpty(delSubIds) && !orderSubService.removeByIds(delSubIds)) {
            throw new ServiceException("修改失败");
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Integer id) {
        SysPurchaseOrder one = this.getById(id);
        if (one == null) {
            throw new ServiceException("已删除");
        }
        SalesConstant.verifyDeleteStatus(one.getStatus());
        QueryWrapper<SysPurchaseOrderSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysPurchaseOrderSub::getOrderId, id);
        if (!orderSubService.remove(queryWrapper)) {
            throw new ServiceException("删除失败");
        }
        if (!this.removeById(id)) {
            throw new ServiceException("删除失败");
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean submit(CommonVo<SysPurchaseOrder, SysPurchaseOrderSub> vo) {
        List<Integer> orderIds = vo.getIds();
        String status = vo.getStatus();
        List<SysPurchaseOrder> orders = this.listByIds(orderIds);
        List<Integer> ids = new ArrayList<>();
        for (SysPurchaseOrder order : orders) {
            SalesConstant.verifySubmitStatus(status, order.getOrderNum(), order.getStatus());
            ids.add(order.getOrderId());
        }
        UpdateWrapper<SysPurchaseOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .set(SysPurchaseOrder::getStatus, status)
                .in(SysPurchaseOrder::getOrderId, ids);
        if (!this.update(updateWrapper)) {
            throw new ServiceException();
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean audit(CommonVo<SysPurchaseOrder, SysPurchaseOrderSub> vo) {
        Integer id = vo.getId();
        SysPurchaseOrder one = this.getById(id);
        if (null == one) {
            throw new ServiceException("该订单不存在");
        }
        String status = vo.getStatus();
        SalesConstant.verifyAuditStatus(status, one.getStatus());
        SysPurchaseOrder order = new SysPurchaseOrder();
        order.setOrderId(id);
        order.setAuditBy(UserSecurityUtil.getNickname());
        order.setAuditTime(new Date());
        order.setStatus(status);
        if (!this.updateById(order)) {
            throw new ServiceException();
        }
        return true;
    }
}
