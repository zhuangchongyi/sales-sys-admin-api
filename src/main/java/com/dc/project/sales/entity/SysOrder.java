package com.dc.project.sales.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 销售订单主表
 *
 * @author zhuangcy
 * @since 2020-09-17
 */
@Data
@Accessors(chain = true)
public class SysOrder extends BaseEntity {

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
     * 交货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deliveryTime;

    /**
     * 订单日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderTime;

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
     * 客户类别id
     */
    private Integer categoryId;

    /**
     * 开单人id
     */
    private Integer personnelId;

    /**
     * 付款条件
     */
    private String payCondition;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 法人
     */
    private String legalPerson;

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
     * 公司简称
     */
    private String abbreviation;

    /**
     * 统一社会信用证号
     */
    private String certificateNum;

    /**
     * 公司地址
     */
    private String address;

    /**
     * 审核人
     */
    private String auditBy;

    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date auditTime;

    /**
     * 税率
     */
    private String taxrate;

    /**
     * 税额
     */
    private String taxamount;

    /**
     * 总计金额
     */
    private BigDecimal totalPrice;

    /**
     * 订单类型
     */
    private String orderType;

    /**
     * 支付状态（1表示支付）
     */
    private Integer payStatus;

    @TableField(exist = false)
    private String personnelName;
    @TableField(exist = false)
    private String personnelNum;
    @TableField(exist = false)
    private String deptName;
    @TableField(exist = false)
    private String deptNum;
    @TableField(exist = false)
    private Integer deptId;

    /**
     * 累计发货金额
     */
    private BigDecimal shipmentPrice;
    /**
     * 累计出库金额
     */
    private BigDecimal outboundPrice;
    /**
     * 累计签收金额（累计应收款金额）
     */
    private BigDecimal signbackPrice;
    /**
     * 累计收款金额
     */
    private BigDecimal receiptPrice;

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public void setShipmentPrice(BigDecimal shipmentPrice) {
        this.shipmentPrice = shipmentPrice;
    }

    public void setOutboundPrice(BigDecimal outboundPrice) {
        this.outboundPrice = outboundPrice;
    }

    public void setSignbackPrice(BigDecimal signbackPrice) {
        this.signbackPrice = signbackPrice;
    }

    public void setReceiptPrice(BigDecimal receiptPrice) {
        this.receiptPrice = receiptPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setPersonnelName(String personnelName) {
        this.personnelName = personnelName;
    }

    public void setPersonnelNum(String personnelNum) {
        this.personnelNum = personnelNum;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public void setClienteleId(Integer clienteleId) {
        this.clienteleId = clienteleId;
    }

    public void setClienteleNum(String clienteleNum) {
        this.clienteleNum = clienteleNum;
    }

    public void setClienteleName(String clienteleName) {
        this.clienteleName = clienteleName;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setPersonnelId(Integer personnelId) {
        this.personnelId = personnelId;
    }

    public void setPayCondition(String payCondition) {
        this.payCondition = payCondition;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAuditBy(String auditBy) {
        this.auditBy = auditBy;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public void setTaxrate(String taxrate) {
        this.taxrate = taxrate;
    }

    public void setTaxamount(String taxamount) {
        this.taxamount = taxamount;
    }
}
