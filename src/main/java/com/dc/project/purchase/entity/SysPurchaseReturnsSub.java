package com.dc.project.purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 采购退货单子表
 *
 * @author zhuangcy
 * @since 2020-12-10
 */
@Data
@Accessors(chain = true)
public class SysPurchaseReturnsSub {

    /**
     * 主键
     */
    @TableId(value = "returns_sub_id", type = IdType.AUTO)
    private Long returnsSubId;

    /**
     * 主键
     */
    private Integer returnsId;

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
     * 本次退货签收数
     */
    private Integer returnsNum;

    /**
     * 本次退货出库数
     */
    private Integer outboundNum;

    /**
     * 已退货数
     */
    private Integer hasReturnsNum;
}
