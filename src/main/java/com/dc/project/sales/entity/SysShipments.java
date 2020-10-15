package com.dc.project.sales.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 销售发货出库主表
 *
 * @author zhuangcy
 * @since 2020-09-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysShipments extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "shipments_id", type = IdType.AUTO)
    private Integer shipmentsId;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 发货单号
     */
    private String shipmentsNum;

    /**
     * 订单单号
     */
    private String orderNum;

    /**
     * 发货日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date shipmentsTime;

    /**
     * 出库日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date outboundTime;

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
     * 发货审批状态
     */
    private String status;
    /**
     * 出库审批状态
     */
    private String auditStatus;

    /**
     * 发货状态
     */
    private String shipmentsStatus;

    /**
     * 出库状态
     */
    private String outboundStatus;

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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date auditTime;

    /**
     * 备注
     */
    private String remark;

    @TableField(exist = false)
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

    public void setShipmentsId(Integer shipmentsId) {
        this.shipmentsId = shipmentsId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setShipmentsNum(String shipmentsNum) {
        this.shipmentsNum = shipmentsNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public void setShipmentsTime(Date shipmentsTime) {
        this.shipmentsTime = shipmentsTime;
    }

    public void setOutboundTime(Date outboundTime) {
        this.outboundTime = outboundTime;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public void setShipmentsStatus(String shipmentsStatus) {
        this.shipmentsStatus = shipmentsStatus;
    }

    public void setOutboundStatus(String outboundStatus) {
        this.outboundStatus = outboundStatus;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public void setWarehouseNum(String warehouseNum) {
        this.warehouseNum = warehouseNum;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public void setAuditBy(String auditBy) {
        this.auditBy = auditBy;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
