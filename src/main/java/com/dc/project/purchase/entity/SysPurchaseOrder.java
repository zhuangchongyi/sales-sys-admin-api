package com.dc.project.purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购订单主表
 *
 * @author zhuangcy
 * @since 2020-12-05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysPurchaseOrder extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    /**
     * 订单号
     */
    private String orderNum;

    /**
     * 订单日期
     */
    private Date orderTime;

    /**
     * 客户id
     */
    private Integer supplierId;

    /**
     * 供应商编码
     */
    private String supplierNum;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 开单人id
     */
    private Integer personnelId;

    /**
     * 开单人名称
     */
    private String personnelName;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 总计金额
     */
    private BigDecimal totalPrice;

    /**
     * 联系人
     */
    private String leader;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 手机号码
     */
    private String mobilephone;

    /**
     * 地址
     */
    private String address;

    /**
     * 审核人
     */
    private String auditBy;

    /**
     * 审核时间
     */
    private Date auditTime;
}
