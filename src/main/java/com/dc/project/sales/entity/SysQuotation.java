package com.dc.project.sales.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 销售报价主表
 *
 * @author zhuangcy
 * @since 2020-09-12
 */
@Accessors(chain = true)
public class SysQuotation extends BaseEntity {
    private static final long serialVersionUID = 89138817133686456L;
    /**
     * 主键
     */
    @TableId(value = "quotation_id", type = IdType.AUTO)
    private Integer quotationId;

    /**
     * 报价单号
     */
    private String quotationNum;

    /**
     * 报价日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date quotationTime;

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
     * 报价人id
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
     * 有效日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date effectiveTime;

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
    private Date auditTime;
    /**
     * 金额
     */
    private BigDecimal totalPrice;

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

    public void setQuotationId(Integer quotationId) {
        this.quotationId = quotationId;
    }

    public void setQuotationNum(String quotationNum) {
        this.quotationNum = quotationNum;
    }

    public void setQuotationTime(Date quotationTime) {
        this.quotationTime = quotationTime;
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

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
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

    public Integer getQuotationId() {
        return quotationId;
    }

    public String getQuotationNum() {
        return quotationNum;
    }

    public Date getQuotationTime() {
        return quotationTime;
    }

    public Integer getClienteleId() {
        return clienteleId;
    }

    public String getClienteleNum() {
        return clienteleNum;
    }

    public String getClienteleName() {
        return clienteleName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getPersonnelId() {
        return personnelId;
    }

    public String getPayCondition() {
        return payCondition;
    }

    public String getStatus() {
        return status;
    }

    public String getRemark() {
        return remark;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public String getLeader() {
        return leader;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getCertificateNum() {
        return certificateNum;
    }

    public String getAddress() {
        return address;
    }

    public String getAuditBy() {
        return auditBy;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getPersonnelName() {
        return personnelName;
    }

    public String getPersonnelNum() {
        return personnelNum;
    }

    public String getDeptName() {
        return deptName;
    }

    public String getDeptNum() {
        return deptNum;
    }

    public Integer getDeptId() {
        return deptId;
    }
}
