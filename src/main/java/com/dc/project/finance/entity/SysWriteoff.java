package com.dc.project.finance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 应收核销表
 *
 * @author zhuangcy
 * @since 2020-10-28
 */
public class SysWriteoff extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "writeoff_id", type = IdType.AUTO)
    private Integer writeoffId;

    /**
     * 核销状态(1已核销)
     */
    private String writeoffStatus;

    /**
     * 核销金额
     */
    private BigDecimal writeoffPrice;

    /**
     * 核销日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date writeoffTime;

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

    /**
     * 部门id
     */
    private Integer deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 部门编码
     */
    private String deptNum;

    /**
     * 业务员id
     */
    private Integer personnelId;

    /**
     * 业务员名称
     */
    private String personnelName;

    /**
     * 业务员编码
     */
    private String personnelNum;

    public Integer getWriteoffId() {
        return writeoffId;
    }

    public void setWriteoffId(Integer writeoffId) {
        this.writeoffId = writeoffId;
    }

    public String getWriteoffStatus() {
        return writeoffStatus;
    }

    public void setWriteoffStatus(String writeoffStatus) {
        this.writeoffStatus = writeoffStatus;
    }

    public BigDecimal getWriteoffPrice() {
        return writeoffPrice;
    }

    public void setWriteoffPrice(BigDecimal writeoffPrice) {
        this.writeoffPrice = writeoffPrice;
    }

    public Date getWriteoffTime() {
        return writeoffTime;
    }

    public void setWriteoffTime(Date writeoffTime) {
        this.writeoffTime = writeoffTime;
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

    public String getDeptNum() {
        return deptNum;
    }

    public void setDeptNum(String deptNum) {
        this.deptNum = deptNum;
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
}
