package com.dc.common.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @Author zhuangcy
 * @Description 产品
 * @Date 2020/9/16 9:53
 */
@Data
@Accessors(chain = true)
public class MaterielVo {

    /**
     * 产品编号
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
     * 单位
     */
    private Integer unitsId;
    private String unitsNum;
    private String unitsName;

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
     * 单价
     */
    private BigDecimal price;

    /**
     * 产品类别
     */
    private Integer categoryId;
    private String categoryNum;
    private String categoryName;

    /**
     * 技术要求
     */
    private String demand;

    /**
     * 型号主键
     */
    private Long pkId;

    /**
     * 仓库
     */
    private Integer warehouseId;
    private String warehouseNum;
    private String warehouseName;
}
