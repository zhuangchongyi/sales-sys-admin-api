package com.dc.project.sales.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;

import java.math.BigDecimal;

/**
 * 销售签回子表
 *
 * @author zhuangcy
 * @since 2020-10-10
 */
public class SysSignbackSub extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "sub_id", type = IdType.AUTO)
    private Long subId;

    /**
     * 主表id
     */
    private Integer signbackId;

    /**
     * 发货单主表id
     */
    private Integer shipmentsId;

    /**
     * 产品id
     */
    private Integer materielId;

    /**
     * 产品编码
     */
    private String materielNum;

    /**
     * 产品名称
     */
    private String materielName;

    /**
     * 规格
     */
    private String specification;

    /**
     * 型号
     */
    private String modelName;

    /**
     * 所需扭矩
     */
    private String needTorque;

    /**
     * 输出扭矩
     */
    private String outTorque;

    /**
     * 基本单位id
     */
    private Integer unitsId;

    /**
     * 单位
     */
    private String unitsName;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 订购数量
     */
    private Integer number;

    /**
     * 金额
     */
    private BigDecimal totalPrice;

    /**
     * 技术要求
     */
    private String demand;

    /**
     * 本次签收数量
     */
    private Integer signNum;

    /**
     * 本次发货数量
     */
    private Integer shipmentNum;

    /**
     * 本次出库数量
     */
    private Integer outboundNum;

    /**
     * 订单子表id
     */
    private Long orderSubId;

    /**
     * 拒收数
     */
    private Integer rejectionNum;

    public void setRejectionNum(Integer rejectionNum) {
        this.rejectionNum = rejectionNum;
    }

    public Integer getRejectionNum() {
        return rejectionNum;
    }

    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public Integer getSignbackId() {
        return signbackId;
    }

    public void setSignbackId(Integer signbackId) {
        this.signbackId = signbackId;
    }

    public Integer getMaterielId() {
        return materielId;
    }

    public void setMaterielId(Integer materielId) {
        this.materielId = materielId;
    }

    public String getMaterielNum() {
        return materielNum;
    }

    public void setMaterielNum(String materielNum) {
        this.materielNum = materielNum;
    }

    public String getMaterielName() {
        return materielName;
    }

    public void setMaterielName(String materielName) {
        this.materielName = materielName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getNeedTorque() {
        return needTorque;
    }

    public void setNeedTorque(String needTorque) {
        this.needTorque = needTorque;
    }

    public String getOutTorque() {
        return outTorque;
    }

    public void setOutTorque(String outTorque) {
        this.outTorque = outTorque;
    }

    public Integer getUnitsId() {
        return unitsId;
    }

    public void setUnitsId(Integer unitsId) {
        this.unitsId = unitsId;
    }

    public String getUnitsName() {
        return unitsName;
    }

    public void setUnitsName(String unitsName) {
        this.unitsName = unitsName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public Integer getSignNum() {
        return signNum;
    }

    public void setSignNum(Integer signNum) {
        this.signNum = signNum;
    }

    public Integer getShipmentNum() {
        return shipmentNum;
    }

    public void setShipmentNum(Integer shipmentNum) {
        this.shipmentNum = shipmentNum;
    }

    public Integer getOutboundNum() {
        return outboundNum;
    }

    public void setOutboundNum(Integer outboundNum) {
        this.outboundNum = outboundNum;
    }

    public Long getOrderSubId() {
        return orderSubId;
    }

    public void setOrderSubId(Long orderSubId) {
        this.orderSubId = orderSubId;
    }

    public Integer getShipmentsId() {
        return shipmentsId;
    }

    public void setShipmentsId(Integer shipmentsId) {
        this.shipmentsId = shipmentsId;
    }
}
