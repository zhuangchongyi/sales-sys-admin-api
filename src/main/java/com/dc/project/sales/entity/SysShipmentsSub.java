package com.dc.project.sales.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 销售发货出库子表
 *
 * @author zhuangcy
 * @since 2020-09-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysShipmentsSub extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "sub_id", type = IdType.AUTO)
    private Long subId;

    /**
     * 订单子表id
     */
    private Long orderSubId;

    /**
     * 主表id
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
     * 下单数量
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

    /**
     * 本次发货数量
     */
    private Integer shipmentNum;

    /**
     * 本次出库数量
     */
    private Integer outboundNum;

    public void setOrderSubId(Long orderSubId) {
        this.orderSubId = orderSubId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public void setShipmentsId(Integer shipmentsId) {
        this.shipmentsId = shipmentsId;
    }

    public void setMaterielId(Integer materielId) {
        this.materielId = materielId;
    }

    public void setMaterielNum(String materielNum) {
        this.materielNum = materielNum;
    }

    public void setMaterielName(String materielName) {
        this.materielName = materielName;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setNeedTorque(String needTorque) {
        this.needTorque = needTorque;
    }

    public void setOutTorque(String outTorque) {
        this.outTorque = outTorque;
    }

    public void setUnitsId(Integer unitsId) {
        this.unitsId = unitsId;
    }

    public void setUnitsName(String unitsName) {
        this.unitsName = unitsName;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public void setShipmentNum(Integer shipmentNum) {
        this.shipmentNum = shipmentNum;
    }

    public void setOutboundNum(Integer outboundNum) {
        this.outboundNum = outboundNum;
    }

    public void setHasShipmentNum(Integer hasShipmentNum) {
        this.hasShipmentNum = hasShipmentNum;
    }
}
