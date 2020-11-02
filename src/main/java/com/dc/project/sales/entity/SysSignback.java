package com.dc.project.sales.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 销售签回主表
 *
 * @author zhuangcy
 * @since 2020-10-10
 */
public class SysSignback extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "signback_id", type = IdType.AUTO)
    private Integer signbackId;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 发货单id
     */
    private Integer shipmentsId;

    /**
     * 签回单号
     */
    private String signbackNum;

    /**
     * 发货单号
     */
    private String shipmentsNum;

    /**
     * 订单单号
     */
    private String orderNum;

    /**
     * 签回日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date signbackTime;

    /**
     * 拒收处理方式
     */
    private String processMode;

    /**
     * 拒收数
     */
    private Integer rejectionNum;

    /**
     * 签收数
     */
    private Integer signNum;

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
     * 签回人id
     */
    private Integer personnelId;

    /**
     * 签回人
     */
    private String personnelName;

    /**
     * 状态
     */
    private String status;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date auditTime;

    /**
     * 备注
     */
    private String remark;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderTime;

    /**
     * 签收状态
     */
    private String signbackStatus;

    /**
     * 累计金额
     */
    private BigDecimal totalPrice;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSignbackStatus() {
        return signbackStatus;
    }

    public void setSignbackStatus(String signbackStatus) {
        this.signbackStatus = signbackStatus;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getSignbackId() {
        return signbackId;
    }

    public void setSignbackId(Integer signbackId) {
        this.signbackId = signbackId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getShipmentsId() {
        return shipmentsId;
    }

    public void setShipmentsId(Integer shipmentsId) {
        this.shipmentsId = shipmentsId;
    }

    public String getSignbackNum() {
        return signbackNum;
    }

    public void setSignbackNum(String signbackNum) {
        this.signbackNum = signbackNum;
    }

    public String getShipmentsNum() {
        return shipmentsNum;
    }

    public void setShipmentsNum(String shipmentsNum) {
        this.shipmentsNum = shipmentsNum;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getSignbackTime() {
        return signbackTime;
    }

    public void setSignbackTime(Date signbackTime) {
        this.signbackTime = signbackTime;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProcessMode() {
        return processMode;
    }

    public void setProcessMode(String processMode) {
        this.processMode = processMode;
    }

    public Integer getRejectionNum() {
        return rejectionNum;
    }

    public void setRejectionNum(Integer rejectionNum) {
        this.rejectionNum = rejectionNum;
    }

    public Integer getSignNum() {
        return signNum;
    }

    public void setSignNum(Integer signNum) {
        this.signNum = signNum;
    }
}
