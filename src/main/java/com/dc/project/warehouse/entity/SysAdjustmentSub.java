package com.dc.project.warehouse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 调整单子表
 *
 * @author zhuangcy
 * @since 2020-09-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysAdjustmentSub extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "sub_id", type = IdType.AUTO)
    private Long subId;

    /**
     * 主表id
     */
    private Integer adjustmentId;

    /**
     * 仓库id
     */
    private Integer warehouseId;

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
     * 单价
     */
    private BigDecimal price;

    /**
     * 单位
     */
    private String unitsName;

    /**
     * 现存量
     */
    private Integer number;
    /**
     * 金额
     */
    private BigDecimal totalPrice;
    /**
     * 调整数量
     */
    private Integer adjustNum;
    /**
     * 调整金额
     */
    private BigDecimal adjustPrice;
    /**
     * 调整前总数量
     */
    private Integer adjustNumber;
    /**
     * 调整前总金额
     */
    private BigDecimal adjustTotalPrice;
    /**
     * 调整原因
     */
    private String remark;


    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public void setAdjustmentId(Integer adjustmentId) {
        this.adjustmentId = adjustmentId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
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

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setUnitsName(String unitsName) {
        this.unitsName = unitsName;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setAdjustNum(Integer adjustNum) {
        this.adjustNum = adjustNum;
    }

    public void setAdjustPrice(BigDecimal adjustPrice) {
        this.adjustPrice = adjustPrice;
    }

    public void setAdjustNumber(Integer adjustNumber) {
        this.adjustNumber = adjustNumber;
    }

    public void setAdjustTotalPrice(BigDecimal adjustTotalPrice) {
        this.adjustTotalPrice = adjustTotalPrice;
    }
}
