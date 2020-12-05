package com.dc.project.purchase.entity;

import com.dc.common.vo.BaseEntity;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
* 采购到货单子表
*
* @author zhuangcy
* @since 2020-12-05
*/
public class SysPurchaseSignSub extends BaseEntity {

    /**
    * 主键
    */
    @TableId(value = "sign_sub_id", type = IdType.AUTO)
    private Long signSubId;

    /**
    * 主键
    */
    private Integer signId;

    /**
    * 主键
    */
    private Long orderSubId;

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
    * 数量
    */
    private Integer number;

    /**
    * 本次到货签收数
    */
    private Integer signNum;
    public Long getSignSubId() {
        return signSubId;
    }
    public void setSignSubId(Long signSubId) {
        this.signSubId = signSubId;
    }
    public Integer getSignId() {
        return signId;
    }
    public void setSignId(Integer signId) {
        this.signId = signId;
    }
    public Long getOrderSubId() {
        return orderSubId;
    }
    public void setOrderSubId(Long orderSubId) {
        this.orderSubId = orderSubId;
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
    public Integer getSignNum() {
        return signNum;
    }
    public void setSignNum(Integer signNum) {
        this.signNum = signNum;
    }
    @Override
    public String toString() {
    return "SysPurchaseSignSub{" +
        "signSubId=" + signSubId +
        ", signId=" + signId +
        ", orderSubId=" + orderSubId +
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
        ", signNum=" + signNum +
        "}";
    }
}
