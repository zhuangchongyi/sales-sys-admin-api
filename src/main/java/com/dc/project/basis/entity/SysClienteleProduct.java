package com.dc.project.basis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 客户产品表
 *
 * @author zhuangchongyi
 * @since 2020-09-04
 */
@Data
@Accessors(chain = true)
public class SysClienteleProduct extends BaseEntity {
    /**
     * 主键
     */
    @TableId(value = "product_id", type = IdType.AUTO)
    private Long productId;

    /**
     * 产品编号
     */
    private Integer materielId;

    /**
     * 客户id
     */
    @NotNull(message = "未找到客户信息")
    private Integer clienteleId;

    /**
     * 基本单位id
     */
    private Integer unitsId;

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
     * 单位
     */
    private String unitsName;

    /**
     * 单价
     */
    private BigDecimal price;


    private Integer categoryId;

    private String categoryName;

    private Long pkId;
}
