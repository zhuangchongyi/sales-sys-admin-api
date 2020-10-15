package com.dc.project.warehouse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 产品入库子表
 *
 * @author zhuangcy
 * @since 2020-09-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysStorageSub extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "sub_id", type = IdType.AUTO)
    private Long subId;

    /**
     * 入库主表id
     */
    private Integer storageId;

    /**
     * 入库主表id
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
     * 单位
     */
    private String unitsName;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 现存数量
     */
    private Integer number;

    /**
     * 金额
     */
    private BigDecimal totalPrice;

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public void setStorageId(Integer storageId) {
        this.storageId = storageId;
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

    public void setUnitsName(String unitsName) {
        this.unitsName = unitsName;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
