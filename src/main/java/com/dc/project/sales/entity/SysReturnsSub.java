package com.dc.project.sales.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;

import java.math.BigDecimal;

/**
 * 销售退货子表
 *
 * @author zhuangcy
 * @since 2020-10-30
 */
public class SysReturnsSub extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "sub_id", type = IdType.AUTO)
    private Long subId;

    /**
     * 主表id
     */
    private Integer returnsId;

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
     * 技术要求
     */
    private String demand;

    /**
     * 已发货数量
     */
    private Integer hasShipmentNum;
    private Integer hasOutboundNum;

    /**
     * 已签收数量
     */
    private Integer hasSignbackNum;

    /**
     * 已退货数量
     */
    private Integer hasReturnsNum;

    /**
     * 退货金额
     */
    private BigDecimal totalPrice;

    /**
     * 实际入库数量
     */
    private Integer realityNum;

    /**
     * 本次退货数量
     */
    private Integer returnsNum;

    /**
     * 订单子表id
     */
    private Long orderSubId;

    public Integer getHasOutboundNum() {
        return hasOutboundNum;
    }

    public void setHasOutboundNum(Integer hasOutboundNum) {
        this.hasOutboundNum = hasOutboundNum;
    }

    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public Integer getReturnsId() {
        return returnsId;
    }

    public void setReturnsId(Integer returnsId) {
        this.returnsId = returnsId;
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

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public Integer getHasShipmentNum() {
        return hasShipmentNum;
    }

    public void setHasShipmentNum(Integer hasShipmentNum) {
        this.hasShipmentNum = hasShipmentNum;
    }

    public Integer getHasSignbackNum() {
        return hasSignbackNum;
    }

    public void setHasSignbackNum(Integer hasSignbackNum) {
        this.hasSignbackNum = hasSignbackNum;
    }

    public Integer getHasReturnsNum() {
        return hasReturnsNum;
    }

    public void setHasReturnsNum(Integer hasReturnsNum) {
        this.hasReturnsNum = hasReturnsNum;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getRealityNum() {
        return realityNum;
    }

    public void setRealityNum(Integer realityNum) {
        this.realityNum = realityNum;
    }

    public Integer getReturnsNum() {
        return returnsNum;
    }

    public void setReturnsNum(Integer returnsNum) {
        this.returnsNum = returnsNum;
    }

    public Long getOrderSubId() {
        return orderSubId;
    }

    public void setOrderSubId(Long orderSubId) {
        this.orderSubId = orderSubId;
    }

    @Override
    public String toString() {
        return "SysReturnsSub{" +
                "subId=" + subId +
                ", returnsId=" + returnsId +
                ", materielId=" + materielId +
                ", materielNum=" + materielNum +
                ", materielName=" + materielName +
                ", specification=" + specification +
                ", modelName=" + modelName +
                ", needTorque=" + needTorque +
                ", outTorque=" + outTorque +
                ", unitsId=" + unitsId +
                ", unitsName=" + unitsName +
                ", price=" + price +
                ", number=" + number +
                ", demand=" + demand +
                ", hasShipmentNum=" + hasShipmentNum +
                ", hasSignbackNum=" + hasSignbackNum +
                ", hasReturnsNum=" + hasReturnsNum +
                ", totalPrice=" + totalPrice +
                ", realityNum=" + realityNum +
                ", returnsNum=" + returnsNum +
                ", orderSubId=" + orderSubId +
                "}";
    }
}
