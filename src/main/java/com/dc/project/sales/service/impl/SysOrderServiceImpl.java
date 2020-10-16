package com.dc.project.sales.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.constant.SalesConstant;
import com.dc.common.exception.ServiceException;
import com.dc.common.utils.BeanUtil;
import com.dc.common.utils.CodeUtil;
import com.dc.common.utils.ObjectMapperUtil;
import com.dc.common.utils.UserSecurityUtils;
import com.dc.project.sales.dao.SysOrderDao;
import com.dc.project.sales.entity.SysOrder;
import com.dc.project.sales.entity.SysOrderSub;
import com.dc.project.sales.service.ISysOrderService;
import com.dc.project.sales.service.ISysOrderSubService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 销售订单主表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-09-17
 */
@Service
public class SysOrderServiceImpl extends ServiceImpl<SysOrderDao, SysOrder> implements ISysOrderService {
    @Autowired
    private ISysOrderSubService orderSubService;

    @Override
    public IPage<SysOrder> page(Page page, SysOrder order) {
        return baseMapper.page(page, order);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, Object> saveAndUpdate(Map formMap) throws Exception {
        Object clienteleForm = formMap.get("clientele");
        Object materielListForm = formMap.get("materielList");
        Object delSubIdsForm = formMap.get("delSubIds");
        if (null == clienteleForm || null == materielListForm)
            throw new ServiceException("保存失败");
        SysOrder order = new SysOrder();
        BeanUtil.register();
        BeanUtils.populate(order, ObjectMapperUtil.toObject(clienteleForm.toString(), Map.class));
        if (null == order.getOrderId()) { //保存主表
            order.setOrderNum(CodeUtil.getCode(SalesConstant.SALES_ORDER_NO));
            if (null == order.getClienteleId() || !this.save(order))
                throw new ServiceException("保存失败");
        } else {
            // 修改主表
            updateOrder(order);
        }
        //修改子表
        saveAndUpdateQuotationSub(order, ObjectMapperUtil.toObject(materielListForm.toString(), List.class));
        // 删除子表
        List<Long> delSubIds = ObjectMapperUtil.toObject(delSubIdsForm.toString(), List.class);
        if (!delSubIds.isEmpty()) {
            orderSubService.removeByIds(delSubIds);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("orderNum", order.getOrderNum());
        result.put("orderId", order.getOrderId());
        return result;
    }

    private void saveAndUpdateQuotationSub(SysOrder order, List<Map> materielList) throws InvocationTargetException, IllegalAccessException {
        if (null == materielList && materielList.isEmpty())
            throw new ServiceException("保存失败,未添加产品");
        List<SysOrderSub> updateList = new ArrayList<>();
        List<SysOrderSub> addList = new ArrayList<>();
        for (Map map : materielList) {
            SysOrderSub orderSub = new SysOrderSub();
            BeanUtils.populate(orderSub, map);
            orderSub.setOrderId(order.getOrderId());
            if (null == orderSub.getSubId())
                addList.add(orderSub);
            else
                updateList.add(orderSub);
        }
        if (!updateList.isEmpty()) {
            if (!orderSubService.updateBatchById(updateList)) {
                throw new ServiceException("修改失败");
            }
        }
        if (!addList.isEmpty()) {
            if (!orderSubService.saveBatch(addList))
                throw new ServiceException("保存失败");
            //TODO 带出产品的图片另外做保存

        }
    }

    private void updateOrder(SysOrder order) {
        if (null == order.getClienteleId() || !this.updateById(order))
            throw new ServiceException("修改失败");
    }

    @Override
    public SysOrder get(Integer orderId) {
        return baseMapper.get(orderId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Integer orderId) {
        SysOrder order = this.getOne(new QueryWrapper<SysOrder>().select("status").eq("order_id", orderId));
        if (null == order) throw new ServiceException("已删除");
        String status = order.getStatus();
        if (SalesConstant.AUDIT.equals(status)
                || SalesConstant.RATIFY.equals(status)
                || SalesConstant.NO_AUDIT.equals(status)
                || SalesConstant.NO_RATIFY.equals(status)
                || SalesConstant.SUBMIT.equals(status)) {
            throw new ServiceException("请收回再进行删除");
        }
        if (!this.removeById(orderId))
            throw new ServiceException(String.format("%s,删除失败" + orderId));
        // 查询子表信息
        if (!orderSubService.remove(new QueryWrapper<SysOrderSub>().eq("order_id", orderId)))
            throw new ServiceException("删除失败");

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean submit(Integer[] ids, String status) {
        if (ids.length == 0)
            throw new ServiceException("未选择数据");
        List<SysOrder> list = this.list(new QueryWrapper<SysOrder>().select("order_id,order_num,status").in("order_id", Arrays.asList(ids)));
        if (list == null || list.isEmpty())
            throw new ServiceException("操作失败");
        ArrayList<Integer> idList = new ArrayList<>();
        for (SysOrder order : list) {
            SalesConstant.verifySubmitStatus(status, order.getOrderNum(), order.getStatus());
            idList.add(order.getOrderId());
        }
        UpdateWrapper<SysOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", status).in("order_id", idList);
        return this.update(updateWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean audit(SysOrder order) {
        SysOrder sysQuotation = this.getOne(new QueryWrapper<SysOrder>().select("order_id,status").eq("order_id", order.getOrderId()));
        SalesConstant.verifyAuditStatus(order.getStatus(), sysQuotation.getStatus());
        order.setAuditTime(new Date());
        order.setAuditBy(UserSecurityUtils.getUsername());
        return this.updateById(order);
    }

    @Override
    public IPage<SysOrder> list(Page page, SysOrder order) {
        return baseMapper.list(page, order);
    }
}
