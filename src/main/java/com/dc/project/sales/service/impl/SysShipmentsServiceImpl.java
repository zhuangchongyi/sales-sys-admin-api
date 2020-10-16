package com.dc.project.sales.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.constant.CustomConstant;
import com.dc.common.constant.SalesConstant;
import com.dc.common.exception.ServiceException;
import com.dc.common.utils.*;
import com.dc.project.sales.dao.SysShipmentsDao;
import com.dc.project.sales.entity.*;
import com.dc.project.sales.service.*;
import com.dc.project.warehouse.entity.SysRepertory;
import com.dc.project.warehouse.service.ISysRepertoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 销售发货出库主表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-09-21
 */
@Slf4j
@Service
public class SysShipmentsServiceImpl extends ServiceImpl<SysShipmentsDao, SysShipments> implements ISysShipmentsService {
    @Autowired
    private ISysShipmentsSubService shipmentsSubService;
    @Autowired
    private ISysRepertoryService repertoryService;
    @Autowired
    private ISysOrderSubService orderSubService;
    @Autowired
    private ISysSignbackService signbackService;
    @Autowired
    private ISysSignbackSubService signbackSubService;

    @Override
    public IPage<SysShipments> page(Page page, SysShipments shipments) {
        return baseMapper.page(page, shipments);
    }

    @Override
    public SysShipments get(Integer shipmentId) {
        return baseMapper.get(shipmentId);
    }

    @Override
    public boolean saveAndUpdate(Map formMap) throws Exception {
        Object clienteleForm = formMap.get("clientele");
        Object materielListForm = formMap.get("materielList");
        Object delSubIdsForm = formMap.get("delSubIds");
        if (null == clienteleForm || null == materielListForm)
            throw new ServiceException("保存失败");
        SysShipments sysShipments = new SysShipments();
        BeanUtil.register();
        BeanUtils.populate(sysShipments, ObjectMapperUtil.toObject(clienteleForm.toString(), Map.class));
        if (null == sysShipments.getShipmentsId()) {
            sysShipments.setShipmentsNum(CodeUtil.getCode(SalesConstant.SALES_SHIPMENTS_NO));
            if (SalesConstant.AUDIT.equals(sysShipments.getStatus()) ||
                    SalesConstant.RATIFY.equals(sysShipments.getStatus())) { //已审核订单发货
                sysShipments.setStatus(SalesConstant.SAVE);
                sysShipments.setShipmentsTime(new Date());
                sysShipments.setRemark(null);
            }
            boolean save = this.save(sysShipments);
            if (!save) throw new ServiceException("保存失败");
        } else {
            update(sysShipments);
        }
        List<Map<String, Object>> subList = ObjectMapperUtil.toObject(materielListForm.toString(), List.class);
        ArrayList<SysShipmentsSub> addSubs = new ArrayList<>();
        ArrayList<SysShipmentsSub> updateSubs = new ArrayList<>();
        for (Map<String, Object> map : subList) {
            SysShipmentsSub sub = new SysShipmentsSub();
            BeanUtils.populate(sub, map);
            if (null != sub.getShipmentNum() && 0 != sub.getShipmentNum()) {
                sub.setOutboundNum(sub.getShipmentNum());
                if (null == sub.getSubId()) {
                    sub.setShipmentsId(sysShipments.getShipmentsId());
                    addSubs.add(sub);
                } else {
                    updateSubs.add(sub);
                }
            }
        }
        insertAndUpdateSub(addSubs);
        insertAndUpdateSub(updateSubs);
        if (null != delSubIdsForm) {
            List<Long> delSubIds = ObjectMapperUtil.toObject(delSubIdsForm.toString(), List.class);
            if (delSubIds.isEmpty()) {
                return true;
            }
            shipmentsSubService.removeByIds(delSubIds);
        }
        return true;
    }

    private void insertAndUpdateSub(ArrayList<SysShipmentsSub> subs) {
        if (!subs.isEmpty()) {
            for (SysShipmentsSub sub : subs) {
                if (null == sub.getSubId()) {
                    shipmentsSubService.save(sub);
                } else {
                    UpdateWrapper<SysShipmentsSub> uw = new UpdateWrapper<>();
                    uw.set(null != sub.getShipmentNum(), "shipment_num", sub.getShipmentNum());
                    uw.set(null != sub.getOutboundNum(), "outbound_num", sub.getOutboundNum());
                    uw.eq("sub_id", sub.getSubId());
                    shipmentsSubService.update(uw);
                }
            }
        }
    }

    private void update(SysShipments sysShipments) {
        UpdateWrapper<SysShipments> uw = new UpdateWrapper<>();
        uw.set(null != sysShipments.getShipmentsTime(), "shipments_time", sysShipments.getShipmentsTime());
        uw.set(null != sysShipments.getOutboundTime(), "outbound_time", sysShipments.getOutboundTime());
        uw.set(null != sysShipments.getPersonnelId(), "personnel_id", sysShipments.getPersonnelId());
        uw.eq("shipments_id", sysShipments.getShipmentsId());
        this.update(uw);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Integer shipmentsId) {
        SysShipments shipments = this.getById(shipmentsId);
        if (null == shipments) throw new ServiceException("已删除删除");
        SalesConstant.verifyDeleteStatus(shipments.getStatus());
        if (!this.removeById(shipmentsId))
            throw new ServiceException(String.format("%s,删除失败", shipments.getShipmentsNum()));
        if (!shipmentsSubService.remove(new QueryWrapper<SysShipmentsSub>().eq("shipments_id", shipmentsId)))
            throw new ServiceException("删除失败");
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean submit(Integer[] ids, String status) {
        if (ids.length == 0 || StringUtils.isEmpty(status))
            throw new ServiceException("操作失败");
        List<SysShipments> list = this.list(new QueryWrapper<SysShipments>()
                .select("shipments_id,shipments_num,status").in("shipments_id", Arrays.asList(ids)));
        if (list.isEmpty())
            throw new ServiceException("操作失败");
        ArrayList<Integer> idList = new ArrayList<>();
        for (SysShipments shipments : list) {
            SalesConstant.verifySubmitStatus(status, shipments.getShipmentsNum(), shipments.getStatus());
            idList.add(shipments.getShipmentsId());
        }
        UpdateWrapper<SysShipments> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", status).in("shipments_id", idList);
        return this.update(updateWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean audit(SysShipments shipments) {
        if (null == shipments.getShipmentsId() || StringUtils.isEmpty(shipments.getStatus()))
            throw new ServiceException();
        SysShipments sysShipments = this.getById(shipments.getShipmentsId());
        if (null == sysShipments) throw new ServiceException();
        shipments.setAuditTime(new Date());
        shipments.setAuditBy(UserSecurityUtils.getUsername());
        List<SysShipmentsSub> subList = shipmentsSubService.list(new QueryWrapper<SysShipmentsSub>()
                .eq("shipments_id", shipments.getShipmentsId()));
        String shipmentsStatus = sysShipments.getShipmentsStatus();//当前发货状态
        String outboundStatus = sysShipments.getOutboundStatus();//当前出库状态
        String status = sysShipments.getStatus();//当前发货审批状态
        String auditStatus = sysShipments.getAuditStatus();//当前出库审批状态
        if (SalesConstant.AUDIT.equals(shipments.getStatus())) { //审核时
            if (CustomConstant.NO_STATUS.equals(shipmentsStatus) &&
                    CustomConstant.NO_STATUS.equals(outboundStatus)) {
                //未发货未出库时
                SalesConstant.verifyAuditStatus(shipments.getStatus(), status);
                shipments.setShipmentsStatus(CustomConstant.YES_STATUS);
                shipments.setStatus(SalesConstant.AUDIT);
                //回写订单子表
                this.updateOrderSubs(SalesConstant.AUDIT, shipmentsStatus, outboundStatus, subList);
            } else if (CustomConstant.YES_STATUS.equals(shipmentsStatus) &&
                    CustomConstant.NO_STATUS.equals(outboundStatus)) {
                // 发货未出库时
                SalesConstant.verifyAuditStatus(shipments.getAuditStatus(), auditStatus);
                shipments.setAuditStatus(SalesConstant.AUDIT);
                shipments.setOutboundStatus(CustomConstant.YES_STATUS);
                // 扣减相应产品的库存
                repertoryService.saveAndUpdate(this.transformRepertory(shipments, subList));
                // 生成签回单
                this.insertSignback(sysShipments, subList);
                //回写订单子表
                this.updateOrderSubs(SalesConstant.AUDIT, shipmentsStatus, outboundStatus, subList);
            } else {
                throw new ServiceException("审核失败,未找到校验规则");
            }
        } else { // 反审核时
            if (CustomConstant.YES_STATUS.equals(shipmentsStatus) &&
                    CustomConstant.YES_STATUS.equals(outboundStatus)) {
                //发货出库时
                SalesConstant.verifyAuditStatus(shipments.getAuditStatus(), auditStatus);
                if (SalesConstant.AUDIT.equals(auditStatus) && SalesConstant.SUBMIT.equals(shipments.getAuditStatus())) {
                    shipments.setAuditStatus(SalesConstant.NO_AUDIT);
                    shipments.setOutboundStatus(CustomConstant.NO_STATUS);
                    // 添加相应产品的库存
                    repertoryService.saveAndUpdate(this.transformRepertory(shipments, subList));
                    // 删除签回单
                    this.deleteSignback(sysShipments.getShipmentsId());
                    //回写订单子表
                    this.updateOrderSubs(SalesConstant.NO_AUDIT, shipmentsStatus, outboundStatus, subList);
                } else {
                    throw new ServiceException("已出库，反审核不通过");
                }
            } else if (CustomConstant.NO_STATUS.equals(outboundStatus) &&
                    CustomConstant.YES_STATUS.equals(shipmentsStatus)) {
                // 发货未出库时
                SalesConstant.verifyAuditStatus(shipments.getStatus(), status);
                shipments.setStatus(SalesConstant.NO_AUDIT);
                shipments.setShipmentsStatus(CustomConstant.NO_STATUS);
                //回写订单子表
                this.updateOrderSubs(SalesConstant.NO_AUDIT, shipmentsStatus, outboundStatus, subList);
            } else {
                throw new ServiceException("反审核失败,未找到校验规则");
            }
        }
        return this.updateById(shipments);
    }

    /**
     * 生成签回单
     */
    private void insertSignback(SysShipments shipments, List<SysShipmentsSub> subList) {
        SysSignback signback = new SysSignback();
        BeanUtil.copyBeanProp(signback, shipments);
        signback.setStatus(SalesConstant.SUBMIT);
        signback.setAuditBy(null);
        signback.setAuditTime(null);
        signback.setSignbackNum(CodeUtil.getCode(SalesConstant.SALES_SIGNBACK_NO));
        if (signbackService.save(signback)) {
            for (SysShipmentsSub sub : subList) {
                SysSignbackSub signbackSub = new SysSignbackSub();
                BeanUtil.copyBeanProp(signbackSub, sub);
                signbackSub.setSubId(null);
                signbackSub.setSignbackId(signback.getSignbackId());
                if (!signbackSubService.save(signbackSub)) {
                    throw new ServiceException("生成签回单失败");
                }
            }
        } else {
            throw new ServiceException("生成签回单失败");
        }
    }

    /**
     * 删除签回单
     */
    private void deleteSignback(Integer shipmentsId) {
        QueryWrapper<SysSignback> wrapper = new QueryWrapper<SysSignback>().eq("shipments_id", shipmentsId);
        SysSignback one = signbackService.getOne(wrapper);
        if (null == one) {
            log.info(String.format("为找到发货单id的%s签回单", shipmentsId));
            return;
        }
        if (SalesConstant.AUDIT.equals(one.getStatus())) {
            throw new ServiceException(String.format("反审核失败，已有审核的签回单%s", one.getSignbackNum()));
        }
        signbackService.remove(wrapper);
        signbackSubService.remove(new QueryWrapper<SysSignbackSub>().eq("shipments_id", shipmentsId));
    }

    /**
     * 修改订单子表
     */
    private void updateOrderSubs(String audit, String shipmentsStatus, String outboundStatus, List<SysShipmentsSub> shipmentsSubs) {
        for (SysShipmentsSub sub : shipmentsSubs) {
            SysOrderSub orderSub = orderSubService.getById(sub.getOrderSubId());
            if (null == orderSub)
                throw new ServiceException(String.format("审核失败，未找到产品%s %s %s", sub.getMaterielName(), sub.getMaterielName(), sub.getModelName()));
            SysOrderSub resultSub = new SysOrderSub();
            resultSub.setSubId(orderSub.getSubId());
            if (SalesConstant.AUDIT.equals(audit)) {
                if (CustomConstant.NO_STATUS.equals(shipmentsStatus) &&
                        CustomConstant.NO_STATUS.equals(outboundStatus)) {// 未发货未出库
                    resultSub.setHasShipmentNum(orderSub.getHasShipmentNum() + sub.getShipmentNum());
                    if (resultSub.getHasShipmentNum() > orderSub.getNumber()) {
                        throw new ServiceException(String.format("审批失败，发货数量之和(%s)大于订购数量(%s)", resultSub.getHasShipmentNum(), orderSub.getNumber()));
                    }
                } else if (CustomConstant.YES_STATUS.equals(shipmentsStatus) &&
                        CustomConstant.NO_STATUS.equals(outboundStatus)) {//已发货未出库
                    resultSub.setHasOutboundNum(orderSub.getHasOutboundNum() + sub.getOutboundNum());
                    if (resultSub.getHasOutboundNum() > orderSub.getHasShipmentNum()) {
                        throw new ServiceException(String.format("审批失败，出库数量之和(%s)大于已发货数量(%s)", resultSub.getHasOutboundNum(), orderSub.getHasShipmentNum()));
                    }
                }
            } else {
                if (CustomConstant.YES_STATUS.equals(shipmentsStatus) &&
                        CustomConstant.YES_STATUS.equals(outboundStatus)) {//发货已出库
                    resultSub.setHasOutboundNum(orderSub.getHasOutboundNum() - sub.getOutboundNum());
                } else if (CustomConstant.NO_STATUS.equals(outboundStatus) &&
                        CustomConstant.YES_STATUS.equals(shipmentsStatus)) {//未出库已发货
                    resultSub.setHasShipmentNum(orderSub.getHasShipmentNum() - sub.getShipmentNum());
                }
            }
            orderSubService.updateById(resultSub);
        }
    }

    /**
     * 修改库存
     */
    private List<SysRepertory> transformRepertory(SysShipments shipments, List<SysShipmentsSub> subList) {
        if (subList.isEmpty()) {
            throw new ServiceException("未找到产品信息");
        }
        List<SysRepertory> resultList = new ArrayList<>();
        for (SysShipmentsSub sub : subList) {
            SysRepertory repertory = new SysRepertory();
            BeanUtil.copyBeanProp(repertory, sub);
            repertory.setWarehouseId(shipments.getWarehouseId());
            repertory.setWarehouseName(shipments.getWarehouseName());
            repertory.setWarehouseNum(shipments.getWarehouseNum());
            if (null == repertory.getMaterielId() || null == repertory.getWarehouseId() || StringUtils.isEmpty(repertory.getModelName()))
                throw new ServiceException("系统异常");
            SysRepertory one = repertoryService.getSysRepertory(repertory);
            if (null == one) {
                throw new ServiceException(String.format("该仓库(%s)未找到产品(%s %s)",
                        shipments.getWarehouseNum(), sub.getMaterielNum(), sub.getModelName()));
            } else {
                if (SalesConstant.AUDIT.equals(shipments.getAuditStatus())) {
                    one.setNumber(one.getNumber() - sub.getOutboundNum());
                } else {
                    one.setNumber(one.getNumber() + sub.getOutboundNum());
                }
                one.setTotalPrice(BigDecimalUtil.mul(one.getPrice(), one.getNumber()));
                resultList.add(one);
            }
        }
        return resultList;
    }
}
