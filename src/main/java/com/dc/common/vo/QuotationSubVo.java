package com.dc.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 销售报价子表
 *
 * @author zhuangcy
 * @since 2020-09-12
 */
@Data
@Accessors(chain = true)
public class QuotationSubVo {

    /**
     * 主键
     */
    private Long subId;

    /**
     * 主表id
     */
    private Integer quotationId;

    /**
     * 报价单号
     */
    private String quotationNum;
    /**
     * 报价单号
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date quotationTime;

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
     * 金额
     */
    private BigDecimal totalPrice;

    /**
     * 技术要求
     */
    private String demand;

    /**
     * 产品类别id
     */
    private Integer categoryId;

}
