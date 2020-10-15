package com.dc.project.sales.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 销售退货主表
 *
 * @author zhuangcy
 * @since 2020-10-11
 */
public class SysReturns extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "returns_id", type = IdType.AUTO)
    private Integer returnsId;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 发货单id
     */
    private Integer shipmentsId;

    /**
     * 退货单号
     */
    private String returnsNum;

    /**
     * 订单单号
     */
    private String orderNum;

    /**
     * 发货单号
     */
    private String shipmentsNum;

    /**
     * 退货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date returnsTime;

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
    private String personnelName;

    /**
     * 退货审批状态
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
     * 仓库id
     */
    private Integer warehouseId;

    /**
     * 仓库编码
     */
    private String warehouseNum;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 审核人
     */
    private String auditBy;

    /**
     * 审核时间
     */
    private Date auditTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 退货总金额
     */
    private BigDecimal totalPrice;
    /**
     * 入库状态
     */
    private String storageStatus;
    private Integer storageId;
    private String storageBy;
    /**
     * 退货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date storageTime;
    /**
     * 入库审批状态
     */
    private String auditStatus;

    public Date getStorageTime() {
        return storageTime;
    }

    public void setStorageTime(Date storageTime) {
        this.storageTime = storageTime;
    }

    public Integer getStorageId() {
        return storageId;
    }

    public void setStorageId(Integer storageId) {
        this.storageId = storageId;
    }

    public String getStorageBy() {
        return storageBy;
    }

    public void setStorageBy(String storageBy) {
        this.storageBy = storageBy;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getStorageStatus() {
        return storageStatus;
    }

    public void setStorageStatus(String storageStatus) {
        this.storageStatus = storageStatus;
    }

    public Integer getReturnsId() {
        return returnsId;
    }

    public void setReturnsId(Integer returnsId) {
        this.returnsId = returnsId;
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

    public String getReturnsNum() {
        return returnsNum;
    }

    public void setReturnsNum(String returnsNum) {
        this.returnsNum = returnsNum;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getShipmentsNum() {
        return shipmentsNum;
    }

    public void setShipmentsNum(String shipmentsNum) {
        this.shipmentsNum = shipmentsNum;
    }

    public Date getReturnsTime() {
        return returnsTime;
    }

    public void setReturnsTime(Date returnsTime) {
        this.returnsTime = returnsTime;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(Integer personnelId) {
        this.personnelId = personnelId;
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

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseNum() {
        return warehouseNum;
    }

    public void setWarehouseNum(String warehouseNum) {
        this.warehouseNum = warehouseNum;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPersonnelName() {
        return personnelName;
    }

    public void setPersonnelName(String personnelName) {
        this.personnelName = personnelName;
    }
}
