package com.dc.project.finance.entity;

import com.dc.common.vo.BaseEntity;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 财务应付款主表
*
* @author zhuangcy
* @since 2020-12-11
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysPayable extends BaseEntity {

    /**
    * 主键
    */
    @TableId(value = "payable_id", type = IdType.AUTO)
    private Integer payableId;

    /**
    * 应付单号
    */
    private String payableNum;

    /**
    * 客户id
    */
    private Integer clienteleId;

    /**
    * 客户编码
    */
    private String clienteleNum;

    /**
    * 客户名称
    */
    private String clienteleName;

    /**
    * 状态
    */
    private String status;

    /**
    * 应付金额
    */
    private BigDecimal totalPrice;

    /**
    * 财务日期
    */
    private LocalDateTime financeTime;

    /**
    * 税额
    */
    private String taxamount;

    /**
    * 税率
    */
    private String taxrate;

    /**
    * 发票号
    */
    private String invoice;

    /**
    * 订单号
    */
    private String orderNum;

    /**
    * 订单日期
    */
    private LocalDateTime orderTime;

    /**
    * 退货单号
    */
    private String returnsNum;

    /**
    * 退货日期
    */
    private LocalDateTime returnsTime;

    /**
    * 审核人
    */
    private String auditBy;

    /**
    * 审核时间
    */
    private LocalDateTime auditTime;

    /**
    * 来源类型
    */
    private String sourceType;

    /**
    * 已核销金额
    */
    private BigDecimal hasVerificaPrice;
}
