package com.dc.project.warehouse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 报废单主表
 *
 * @author zhuangcy
 * @since 2020-09-25
 */
public class SysScrap extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "scrap_id", type = IdType.AUTO)
    private Integer scrapId;

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
     * 状态
     */
    private String status;

    /**
     * 报废总金额
     */
    private BigDecimal totalPrice;

    /**
     * 报废时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date scrapTime;

    /**
     * 报废负责人
     */
    private String personnelName;

    /**
     * 报废人id
     */
    private Integer personnelId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 审核者
     */
    private String auditBy;

    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss HH:mm:ss")
    private Date auditTime;

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

    public Integer getScrapId() {
        return scrapId;
    }

    public void setScrapId(Integer scrapId) {
        this.scrapId = scrapId;
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

    public Date getScrapTime() {
        return scrapTime;
    }

    public void setScrapTime(Date scrapTime) {
        this.scrapTime = scrapTime;
    }

    public String getPersonnelName() {
        return personnelName;
    }

    public void setPersonnelName(String personnelName) {
        this.personnelName = personnelName;
    }

    public Integer getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(Integer personnelId) {
        this.personnelId = personnelId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SysScrap{" +
                "scrapId=" + scrapId +
                ", warehouseId=" + warehouseId +
                ", warehouseNum=" + warehouseNum +
                ", warehouseName=" + warehouseName +
                ", status=" + status +
                ", totalPrice=" + totalPrice +
                ", scrapTime=" + scrapTime +
                ", personnelName=" + personnelName +
                ", personnelId=" + personnelId +
                ", remark=" + remark +
                "}";
    }
}
