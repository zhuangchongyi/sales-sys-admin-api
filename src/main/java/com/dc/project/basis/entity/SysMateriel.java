package com.dc.project.basis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 产品档案表
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
@Data
@Accessors(chain = true)
public class SysMateriel extends BaseEntity {

    /**
     * 产品编号
     */
    @TableId(value = "materiel_id", type = IdType.AUTO)
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
     * 产品类型id
     */
    private Integer categoryId;

    /**
     * 规格
     */
    private String specification;

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
     * 最高价
     */
    private BigDecimal maxPrice;

    /**
     * 最低价
     */
    private BigDecimal minPrice;

    /**
     * 状态
     */
    private String status;

    /**
     * 逻辑删除
     */
    @JsonIgnore
    private String delFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 类别
     */
    @TableField(exist = false)
    private SysCategory category;
    /**
     * 单位
     */
    @TableField(exist = false)
    private SysUnits units;

    /**
     * 型号
     */
    @TableField(exist = false)
    private String[] modelNames;

    /**
     * 修改时回显
     *
     * @return
     */
    @JsonGetter
    public String[] getMaterielModels() {
        return this.modelName.split(",");
    }

    /**
     * 列表查看时
     *
     * @return
     */
    @TableField(exist = false)
    private String modelName;


}
