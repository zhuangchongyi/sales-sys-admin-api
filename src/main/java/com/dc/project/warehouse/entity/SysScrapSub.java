package com.dc.project.warehouse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;

import java.math.BigDecimal;

/**
 * 报废单子表
 *
 * @author zhuangcy
 * @since 2020-09-25
 */
public class SysScrapSub extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "sub_id", type = IdType.AUTO)
    private Long subId;

    /**
     * 主表id
     */
    private Integer scrapId;

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
     * 报废数
     */
    private Integer scrapNum;

    /**
     * 报废原因
     */
    private String remark;

    /**
     * 金额
     */
    private BigDecimal totalPrice;

    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public Integer getScrapId() {
        return scrapId;
    }

    public void setScrapId(Integer scrapId) {
        this.scrapId = scrapId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUnitsName() {
        return unitsName;
    }

    public void setUnitsName(String unitsName) {
        this.unitsName = unitsName;
    }

    public Integer getScrapNum() {
        return scrapNum;
    }

    public void setScrapNum(Integer scrapNum) {
        this.scrapNum = scrapNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "SysScrapSub{" +
                "subId=" + subId +
                ", scrapId=" + scrapId +
                ", warehouseId=" + warehouseId +
                ", materielId=" + materielId +
                ", materielNum=" + materielNum +
                ", materielName=" + materielName +
                ", specification=" + specification +
                ", modelName=" + modelName +
                ", needTorque=" + needTorque +
                ", outTorque=" + outTorque +
                ", unitsId=" + unitsId +
                ", price=" + price +
                ", unitsName=" + unitsName +
                ", scrapNum=" + scrapNum +
                ", remark=" + remark +
                ", totalPrice=" + totalPrice +
                "}";
    }
}
