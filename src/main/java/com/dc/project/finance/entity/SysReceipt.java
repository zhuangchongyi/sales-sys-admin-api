package com.dc.project.finance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 财务收款表
 *
 * @author zhuangcy
 * @since 2020-10-23
 */
public class SysReceipt extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "receipt_id", type = IdType.AUTO)
    private Integer receiptId;

    /**
     * 收款单号
     */
    private String receiptNum;

    /**
     * 收款日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date receiptTime;

    /**
     * 收款金额
     */
    private BigDecimal receiptPrice;

    /**
     * 收款账号
     */
    private String receiptAccount;

    /**
     * 收款方式
     */
    private String paymentType;

    /**
     * 付款账号
     */
    private String paymentAccount;

    /**
     * 状态
     */
    private String status;

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
     * 业务员id
     */
    private Integer personnelId;

    /**
     * 业务员id
     */
    private String personnelName;

    /**
     * 业务员id
     */
    private String personnelNum;

    /**
     * 业务部门id
     */
    private Integer deptId;

    /**
     * 业务部门名称
     */
    private String deptName;

    /**
     * 业务部门编码
     */
    private String deptNum;

    /**
     * 核销状态
     */
    private String verificaStatus;

    /**
     * 可核销金额
     */
    private BigDecimal verificaPrice;

    /**
     * 已核销金额
     */
    private BigDecimal hasVerificaPrice;

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
     * 备注
     */
    private String remark;

    public Integer getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Integer receiptId) {
        this.receiptId = receiptId;
    }

    public String getReceiptNum() {
        return receiptNum;
    }

    public void setReceiptNum(String receiptNum) {
        this.receiptNum = receiptNum;
    }

    public BigDecimal getReceiptPrice() {
        return receiptPrice;
    }

    public void setReceiptPrice(BigDecimal receiptPrice) {
        this.receiptPrice = receiptPrice;
    }

    public String getReceiptAccount() {
        return receiptAccount;
    }

    public void setReceiptAccount(String receiptAccount) {
        this.receiptAccount = receiptAccount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(String paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Integer getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(Integer personnelId) {
        this.personnelId = personnelId;
    }

    public String getPersonnelName() {
        return personnelName;
    }

    public void setPersonnelName(String personnelName) {
        this.personnelName = personnelName;
    }

    public String getPersonnelNum() {
        return personnelNum;
    }

    public void setPersonnelNum(String personnelNum) {
        this.personnelNum = personnelNum;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getVerificaStatus() {
        return verificaStatus;
    }

    public void setVerificaStatus(String verificaStatus) {
        this.verificaStatus = verificaStatus;
    }

    public BigDecimal getVerificaPrice() {
        return verificaPrice;
    }

    public void setVerificaPrice(BigDecimal verificaPrice) {
        this.verificaPrice = verificaPrice;
    }

    public BigDecimal getHasVerificaPrice() {
        return hasVerificaPrice;
    }

    public void setHasVerificaPrice(BigDecimal hasVerificaPrice) {
        this.hasVerificaPrice = hasVerificaPrice;
    }

    public String getAuditBy() {
        return auditBy;
    }

    public void setAuditBy(String auditBy) {
        this.auditBy = auditBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(Date receiptTime) {
        this.receiptTime = receiptTime;
    }

    public String getDeptNum() {
        return deptNum;
    }

    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }
}
