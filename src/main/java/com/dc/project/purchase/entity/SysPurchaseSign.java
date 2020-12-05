package com.dc.project.purchase.entity;

import com.dc.common.vo.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;

/**
* 采购到货主表
*
* @author zhuangcy
* @since 2020-12-05
*/
public class SysPurchaseSign extends BaseEntity {

    /**
    * 主键
    */
    @TableId(value = "sign_id", type = IdType.AUTO)
    private Integer signId;

    /**
    * 到货单号
    */
    private String signNum;

    /**
    * 到货日期
    */
    private LocalDateTime signTime;

    /**
    * 订单单号
    */
    private String orderNum;

    /**
    * 订单日期
    */
    private LocalDateTime orderTime;

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
    private LocalDateTime auditTime;
    public Integer getSignId() {
        return signId;
    }
    public void setSignId(Integer signId) {
        this.signId = signId;
    }
    public String getSignNum() {
        return signNum;
    }
    public void setSignNum(String signNum) {
        this.signNum = signNum;
    }
    public LocalDateTime getSignTime() {
        return signTime;
    }
    public void setSignTime(LocalDateTime signTime) {
        this.signTime = signTime;
    }
    public String getOrderNum() {
        return orderNum;
    }
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
    public LocalDateTime getOrderTime() {
        return orderTime;
    }
    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }
    public Integer getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }
    public String getSupplierNum() {
        return supplierNum;
    }
    public void setSupplierNum(String supplierNum) {
        this.supplierNum = supplierNum;
    }
    public String getSupplierName() {
        return supplierName;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getLeader() {
        return leader;
    }
    public void setLeader(String leader) {
        this.leader = leader;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getMobilephone() {
        return mobilephone;
    }
    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAuditBy() {
        return auditBy;
    }
    public void setAuditBy(String auditBy) {
        this.auditBy = auditBy;
    }
    public LocalDateTime getAuditTime() {
        return auditTime;
    }
    public void setAuditTime(LocalDateTime auditTime) {
        this.auditTime = auditTime;
    }
    @Override
    public String toString() {
    return "SysPurchaseSign{" +
        "signId=" + signId +
        ", signNum=" + signNum +
        ", signTime=" + signTime +
        ", orderNum=" + orderNum +
        ", orderTime=" + orderTime +
        ", supplierId=" + supplierId +
        ", supplierNum=" + supplierNum +
        ", supplierName=" + supplierName +
        ", personnelId=" + personnelId +
        ", personnelName=" + personnelName +
        ", status=" + status +
        ", remark=" + remark +
        ", leader=" + leader +
        ", email=" + email +
        ", phone=" + phone +
        ", mobilephone=" + mobilephone +
        ", address=" + address +
        ", auditBy=" + auditBy +
        ", auditTime=" + auditTime +
        "}";
    }
}
