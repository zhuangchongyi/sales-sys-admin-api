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
import com.dc.project.open.vo.OrderVo;
import com.dc.project.finance.service.ISysReceiptService;
import com.dc.project.finance.service.ISysReceivableService;
import com.dc.project.sales.dao.SysShipmentsDao;
import com.dc.project.sales.entity.*;
import com.dc.project.sales.service.*;
import com.dc.project.warehouse.entity.SysRepertory;
import com.dc.project.warehouse.service.ISysRepertoryService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 销售发货出库主表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-09-21
 */
@Service
public class SysShipmentsServiceImpl extends ServiceImpl<SysShipmentsDao, SysShipments> implements ISysShipmentsService {
    @Autowired
    private ISysShipmentsSubService shipmentsSubService;
    @Autowired
    private ISysRepertoryService repertoryService;
    @Autowired
    private ISysOrderService orderService;
    @Autowired
    private ISysOrderSubService orderSubService;
    @Autowired
    private ISysSignbackService signbackService;
    @Autowired
    private ISysSignbackSubService signbackSubService;
    @Autowired
    private ISysReceiptService receiptService;
    @Autowired
    private ISysReceivableService receivableService;


    @DataScope(userAlias = "ss", userColumn = "create_id")
    @Override
    public IPage<SysShipments> page(Page page, SysShipments shipments) {
        return baseMapper.page(page, shipments);
    }

    @Override
    public IPage<SysShipments> outboundPage(Page page, SysShipments shipments) {
        return baseMapper.outboundPage(page, shipments);
    }

    @Override
    public SysShipments get(Integer shipmentId) {
        return baseMapper.get(shipmentId);
    }

    @Transactional(rollbackFor = Exception.class)
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
            if (SalesConstant.AUDIT.equals(sysShipments.getStatus())) { //订单发货
                sysShipments.setStatus(SalesConstant.SUBMIT);
                sysShipments.setShipmentsTime(new Date());
                sysShipments.setRemark(null);
                sysShipments.setAuditBy(null);
                sysShipments.setAuditTime(null);
            }
            if (!this.save(sysShipments)) throw new ServiceException("保存失败");
        } else {
            if (!this.updateById(sysShipments)) throw new ServiceException("保存失败");
        }
        List<Map<String, Object>> subList = ObjectMapperUtil.toObject(materielListForm.toString(), List.class);
        for (Map<String, Object> map : subList) {
            SysShipmentsSub sub = new SysShipmentsSub();
            BeanUtils.populate(sub, map);
            if (null == sub.getOutboundNum() || 0 == sub.getOutboundNum()) {
                sub.setOutboundNum(sub.getShipmentNum());
            }
            if (null == sub.getSubId()) {
                sub.setShipmentsId(sysShipments.getShipmentsId());
                if (!shipmentsSubService.save(sub)) throw new ServiceException();
            } else {
                UpdateWrapper<SysShipmentsSub> uw = new UpdateWrapper<>();
                uw.set(null != sub.getShipmentNum(), "shipment_num", sub.getShipmentNum());
                uw.set(null != sub.getOutboundNum(), "outbound_num", sub.getOutboundNum());
                uw.eq("sub_id", sub.getSubId());
                if (!shipmentsSubService.update(uw)) throw new ServiceException();
            }
        }
        if (null != delSubIdsForm) {
            List<Long> delSubIds = ObjectMapperUtil.toObject(delSubIdsForm.toString(), List.class);
            if (delSubIds.isEmpty()) {
                return true;
            }
            if (!shipmentsSubService.removeByIds(delSubIds)) throw new ServiceException();
        }
        return true;
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
        if (list.isEmpty()) throw new ServiceException();
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
        String checkStatus = shipments.getStatus();// 审核后状态
        if (null == shipments.getShipmentsId() || StringUtils.isEmpty(checkStatus))
            throw new ServiceException();
        SysShipments one = this.getById(shipments.getShipmentsId());
        if (null == one) throw new ServiceException("发货单不存在");
        Integer shipmentsId = one.getShipmentsId();
        Integer orderId = one.getOrderId();
        String nowAuditStatus = one.getAuditStatus();//当前出库审核状态
        String nowStatus = one.getStatus();//当前发货审核状态
        if (SalesConstant.AUDIT.equals(checkStatus) || SalesConstant.RATIFY.equals(checkStatus)) { //审核时
            if (SalesConstant.AUDIT.equals(nowStatus)) {
                throw new ServiceException("已审核");
            } else if (SalesConstant.RATIFY.equals(nowStatus)) {
                throw new ServiceException("已特批");
            } else if (SalesConstant.AUDIT.equals(nowAuditStatus)) {
                throw new ServiceException("已出库");
            }
            // 表示通知已发货
            shipments.setShipmentsStatus(CustomConstant.YES_STATUS);
        } else { // 反审核时
            if (SalesConstant.AUDIT.equals(nowAuditStatus)) {
                if (SalesConstant.NO_RATIFY.equals(checkStatus)) {
                    throw new ServiceException("已出库，取消特批失败");
                } else if (SalesConstant.NO_AUDIT.equals(checkStatus)) {
                    throw new ServiceException("已出库，反审核失败");
                }
            } else if (SalesConstant.NO_AUDIT.equals(nowStatus)) {
                throw new ServiceException("已反审核");
            } else if (SalesConstant.NO_RATIFY.equals(nowStatus)) {
                throw new ServiceException("已取消特批");
            }
            // 表示取消通知发货
            shipments.setShipmentsStatus(CustomConstant.NO_STATUS);
        }
        //回写订单子表
        this.updateOrderSubs(checkStatus, one.getShipmentsStatus(), one.getOutboundStatus(), shipmentsId, orderId);
        shipments.setAuditTime(new Date());
        shipments.setAuditBy(UserSecurityUtil.getUsername());
        return this.updateById(shipments);
    }

    /**
     * 出库单审核
     *
     * @param shipments
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean auditOutbound(SysShipments shipments) {
        String checkAuditStatus = shipments.getAuditStatus();
        if (null == shipments.getShipmentsId() || StringUtils.isEmpty(checkAuditStatus))
            throw new ServiceException();
        SysShipments one = this.getById(shipments.getShipmentsId());
        if (null == one) throw new ServiceException("出库单不存在");
        if (null == one.getWarehouseId()) throw new ServiceException("未选择出库仓库");
        Integer shipmentsId = one.getShipmentsId();
        List<SysShipmentsSub> subList = shipmentsSubService.list(new QueryWrapper<SysShipmentsSub>().eq("shipments_id", shipmentsId));
        if (SalesConstant.AUDIT.equals(checkAuditStatus)) { //出库审核时
            SalesConstant.verifyAuditStatus(checkAuditStatus, one.getAuditStatus());
            // 生成签回单
            this.insertSignback(one, subList);
            shipments.setOutboundStatus(CustomConstant.YES_STATUS);
        } else { // 出库反审核时
            SalesConstant.verifyAuditStatus(checkAuditStatus, one.getAuditStatus());
            // 删除签回单
            this.deleteSignback(shipmentsId);
            shipments.setOutboundStatus(CustomConstant.NO_STATUS);
        }
        //回写订单子表
        this.updateOrderSubs(checkAuditStatus, one.getShipmentsStatus(), one.getOutboundStatus(), shipmentsId, one.getOrderId());
        // 添加或扣减相应产品的库存
        repertoryService.saveAndUpdate(this.transformRepertory(shipments, subList));
        shipments.setAuditTime(new Date());
        shipments.setAuditBy(UserSecurityUtil.getUsername());
        return this.updateById(shipments);
    }

    @Override
    public List<OrderVo> listShipment(Integer clienteleId) {
        return baseMapper.listShipment(clienteleId);
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
            BigDecimal zero = BigDecimalUtil.ZERO;
            int signNum = 0;
            for (SysShipmentsSub sub : subList) {
                SysSignbackSub signbackSub = new SysSignbackSub();
                BeanUtil.copyBeanProp(signbackSub, sub);
                signbackSub.setSubId(null);
                signbackSub.setSignbackId(signback.getSignbackId());
                signbackSub.setSignNum(sub.getOutboundNum());
                signNum += sub.getOutboundNum();
                BigDecimal totalPrice = BigDecimalUtil.mul(sub.getPrice(), signbackSub.getOutboundNum());
                zero = BigDecimalUtil.add(zero, totalPrice);
                signbackSub.setTotalPrice(totalPrice);
                if (!signbackSubService.save(signbackSub)) {
                    throw new ServiceException("生成签回单失败");
                }
            }
            signback.setSignNum(signNum);
            signback.setTotalPrice(zero);
            if (!signbackService.updateById(signback)) {
                throw new ServiceException("生成签回单失败");
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
            log.error(String.format("为找到发货单id的%s签回单", shipmentsId));
            return;
        }
        if (SalesConstant.AUDIT.equals(one.getStatus())) {
            throw new ServiceException(String.format("反审核失败，%s签回单已审核", one.getSignbackNum()));
        }
        signbackService.remove(wrapper);
        signbackSubService.remove(new QueryWrapper<SysSignbackSub>().eq("shipments_id", shipmentsId));
    }

    /**
     * 修改订单子表
     *
     * @param checkStatus     审核状态
     * @param shipmentsStatus 当前发货状态
     * @param outboundStatus  当前出库状态
     * @param shipmentsId     发货单id
     * @param orderId
     */
    private void updateOrderSubs(String checkStatus, String shipmentsStatus, String outboundStatus, Integer shipmentsId, Integer orderId) {
        List<SysShipmentsSub> shipmentsSubs = shipmentsSubService.list(new QueryWrapper<SysShipmentsSub>().eq("shipments_id", shipmentsId));
        SysOrder order = orderService.getById(orderId);
        BigDecimal shipmentPrice = BigDecimalUtil.ZERO; //累计发货金额
        BigDecimal outboundPrice = BigDecimalUtil.ZERO; //累计出库金额
        SysOrderSub resultSub = new SysOrderSub();// 修改订单子表对象
        SysOrder sysOrder = new SysOrder();
        for (SysShipmentsSub sub : shipmentsSubs) {
            SysOrderSub one = orderSubService.getById(sub.getOrderSubId());
            if (null == one) {
                throw new ServiceException(String.format("操作失败，未找到产品(%s %s %s)", sub.getMaterielName(), sub.getMaterielName(), sub.getModelName()));
            }
            String materielNum = one.getMaterielNum();
            Integer nowHasShipmentNum = one.getHasShipmentNum();// 当前已通知发货数量
            Integer nowHasOutboundNum = one.getHasOutboundNum();// 当前已出库数量
            Integer nowHasSignbackNum = one.getHasSignbackNum();// 当前签收的数量
            //Integer returnNum = one.getHasReturnNum(); // 已退货数
            Integer number = one.getNumber();//订购数量
            Integer shipmentNum = sub.getShipmentNum();// 通知发货数量
            Integer outboundNum = sub.getOutboundNum();// 通知出库数量
            //发货审核或者发货特批时
            if (SalesConstant.AUDIT.equals(checkStatus) || SalesConstant.RATIFY.equals(checkStatus)) {
                if (CustomConstant.NO_STATUS.equals(shipmentsStatus) && CustomConstant.NO_STATUS.equals(outboundStatus)) {// 未发货未出库->发货未出库
                    if (shipmentNum > number) { // 发货数不能大于订购数
                        throw new ServiceException(String.format("审核失败，产品%s发货数量(%s)大于订购数量(%s)", materielNum, shipmentNum, number));
                    }
                    int num = number - (nowHasShipmentNum + nowHasOutboundNum + nowHasSignbackNum);//最大发货数量
                    if (shipmentNum > num) {
                        throw new ServiceException(String.format("审核失败，产品%s本次发货数量(%s)大于已发货数量(%s)+出库未签收数量(%s)+已签收数量(%s)之和",
                                materielNum, shipmentNum, nowHasShipmentNum, nowHasOutboundNum, nowHasSignbackNum));
                    }
                    resultSub.setHasShipmentNum(nowHasShipmentNum + shipmentNum);
                    shipmentPrice = BigDecimalUtil.add(shipmentPrice, BigDecimalUtil.mul(one.getPrice(), resultSub.getHasShipmentNum()));
                    sysOrder.setShipmentPrice(shipmentPrice);
                } else if (CustomConstant.YES_STATUS.equals(shipmentsStatus) && CustomConstant.NO_STATUS.equals(outboundStatus)) {//发货未出库->已发货未出库
                    if (outboundNum > shipmentNum) { // 出库数量不能大于通知发货数量
                        throw new ServiceException(String.format("审批失败，产品%s出库数量(%s)大于发货数量(%s)", materielNum, outboundNum, shipmentNum));
                    }
                    resultSub.setHasShipmentNum(nowHasShipmentNum - shipmentNum);
                    resultSub.setHasOutboundNum(nowHasOutboundNum + outboundNum);
                    outboundPrice = BigDecimalUtil.add(outboundPrice, BigDecimalUtil.mul(one.getPrice(), resultSub.getHasOutboundNum()));
                    sysOrder.setOutboundPrice(outboundPrice);
                } else {
                    throw new ServiceException();
                }
            } else { //发货反审核或者取消特批时
                if (CustomConstant.YES_STATUS.equals(shipmentsStatus) && CustomConstant.YES_STATUS.equals(outboundStatus)) {//已发货已出库->已发货未出库
                    int hasOutboundNum = nowHasOutboundNum - outboundNum;
                    if (hasOutboundNum < 0) {
                        throw new ServiceException(String.format("反审核失败，产品%s出库数量(%s)大于已出库数量(%s)", materielNum, hasOutboundNum, nowHasOutboundNum));
                    }
                    resultSub.setHasOutboundNum(hasOutboundNum);
                    resultSub.setHasShipmentNum(nowHasShipmentNum + shipmentNum);
                    BigDecimal price = BigDecimalUtil.add(order.getOutboundPrice(), outboundPrice);
                    if (BigDecimalUtil.compareTo(price, BigDecimalUtil.ZERO) < 0) {
                        throw new ServiceException("金额校验异常");
                    }
                    outboundPrice = BigDecimalUtil.add(outboundPrice, BigDecimalUtil.mul(one.getPrice(), hasOutboundNum));
                    sysOrder.setOutboundPrice(outboundPrice);
                } else if (CustomConstant.NO_STATUS.equals(outboundStatus) && CustomConstant.YES_STATUS.equals(shipmentsStatus)) {//已发货未出库->未发货未出库
                    int hasShipmentNum = nowHasShipmentNum - shipmentNum;
                    if (hasShipmentNum < 0) {
                        throw new ServiceException(String.format("反审核失败，产品%s发货数量(%s)大于已发货数量(%s)", materielNum, hasShipmentNum, nowHasShipmentNum));
                    }
                    resultSub.setHasShipmentNum(hasShipmentNum);
                    shipmentPrice = BigDecimalUtil.add(shipmentPrice, BigDecimalUtil.mul(one.getPrice(), hasShipmentNum));
                    sysOrder.setShipmentPrice(shipmentPrice);
                } else {
                    throw new ServiceException();
                }
            }
            resultSub.setSubId(one.getSubId());
            if (!orderSubService.updateById(resultSub)) {
                throw new ServiceException();
            }
        }
        sysOrder.setOrderId(orderId);
        if (!orderService.updateById(sysOrder)) {
            throw new ServiceException();
        }
        // 审核时校验收款金额
        if (SalesConstant.AUDIT.equals(checkStatus) && CustomConstant.NO_STATUS.equals(shipmentsStatus)) {//未发货时审核
            Integer clienteleId = order.getClienteleId();
            BigDecimal receiptPrice = receiptService.findReceiptPriceByClienteleId(clienteleId);// 未核销的收款
            BigDecimal receivePrice = receivableService.findReceivePriceByClienteleId(clienteleId);//未核销的应收款
            //本次发货金额<=未核销的收款-未核销的应收款
            BigDecimal decimal = BigDecimalUtil.sub(receiptPrice, receivePrice);
            if (BigDecimalUtil.compareTo(shipmentPrice, decimal) > 0) {
                throw new ServiceException(String.format("审核失败，客户本次发货金额(%s)大于客户收款可用金额(%s)，请前往特批", shipmentPrice, decimal));
            }
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
                throw new ServiceException("产品参数异常");
            SysRepertory one = repertoryService.getSysRepertory(repertory);
            if (null == one) {
                if (SalesConstant.AUDIT.equals(shipments.getAuditStatus())) {
                    throw new ServiceException(String.format("该仓库(%s)未找到产品(%s %s)", shipments.getWarehouseNum(), sub.getMaterielNum(), sub.getModelName()));
                } else {
                    repertory.setNumber(sub.getOutboundNum());
                    repertory.setTotalPrice(BigDecimalUtil.mul(sub.getPrice(), sub.getNumber()));
                    resultList.add(repertory);
                }
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
