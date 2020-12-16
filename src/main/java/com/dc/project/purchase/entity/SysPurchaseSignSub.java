package com.dc.project.purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 采购到货单子表
 *
 * @author zhuangcy
 * @since 2020-12-05
 */
@Data
public class SysPurchaseSignSub {

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


    /**
     * 本次到货入库数
     */
    private Integer storageNum;

    /**
     * 已到货签收数
     */
    private Integer hasSignNum;
}
