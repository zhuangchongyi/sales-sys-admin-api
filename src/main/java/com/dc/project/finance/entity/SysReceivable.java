package com.dc.project.finance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 财务应收款主表
 *
 * @author zhuangcy
 * @since 2020-10-21
 */
public class SysReceivable extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "receivable_id", type = IdType.AUTO)
    private Integer receivableId;

    /**
     * 应收单号
     */
    private String receivableNum;

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
     * 累计金额
     */
    private BigDecimal totalPrice;

    /**
     * 应收金额
     */
    private BigDecimal receivePrice;

    /**
     * 财务日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date financeTime;

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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderTime;

    /**
     * 发货单号
     */
    private String shipmentsNum;

    /**
     * 发货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date shipmentsTime;

    /**
     * 签收单号
     */
    private String signbackNum;

    /**
     * 签收日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date signbackTime;

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
     * 来源类型
     */
    private String sourceType;

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getReceivableId() {
        return receivableId;
    }

    public void setReceivableId(Integer receivableId) {
        this.receivableId = receivableId;
    }

    public String getReceivableNum() {
        return receivableNum;
    }

    public void setReceivableNum(String receivableNum) {
        this.receivableNum = receivableNum;
    }

    public Integer getClienteleId() {
        return clienteleId;
    }

    public void setClienteleId(Integer clienteleId) {
        this.clienteleId = clienteleId;
    }

    public String getClienteleNum() {
        return clienteleNum;
    }

    public void setClienteleNum(String clienteleNum) {
        this.clienteleNum = clienteleNum;
    }

    public String getClienteleName() {
        return clienteleName;
    }

    public void setClienteleName(String clienteleName) {
        this.clienteleName = clienteleName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getReceivePrice() {
        return receivePrice;
    }

    public void setReceivePrice(BigDecimal receivePrice) {
        this.receivePrice = receivePrice;
    }

    public Date getFinanceTime() {
        return financeTime;
    }

    public void setFinanceTime(Date financeTime) {
        this.financeTime = financeTime;
    }

    public String getTaxamount() {
        return taxamount;
    }

    public void setTaxamount(String taxamount) {
        this.taxamount = taxamount;
    }

    public String getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(String taxrate) {
        this.taxrate = taxrate;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getShipmentsNum() {
        return shipmentsNum;
    }

    public void setShipmentsNum(String shipmentsNum) {
        this.shipmentsNum = shipmentsNum;
    }

    public Date getShipmentsTime() {
        return shipmentsTime;
    }

    public void setShipmentsTime(Date shipmentsTime) {
        this.shipmentsTime = shipmentsTime;
    }

    public String getSignbackNum() {
        return signbackNum;
    }

    public void setSignbackNum(String signbackNum) {
        this.signbackNum = signbackNum;
    }

    public Date getSignbackTime() {
        return signbackTime;
    }

    public void setSignbackTime(Date signbackTime) {
        this.signbackTime = signbackTime;
    }

    public String getAuditBy() {
        return auditBy;
    }

    public void setAuditBy(String auditBy) {
        this.auditBy = auditBy;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }
}
