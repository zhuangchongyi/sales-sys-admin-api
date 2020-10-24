package com.dc.project.warehouse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 产品入库主表
 *
 * @author zhuangcy
 * @since 2020-09-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysStorage extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "storage_id", type = IdType.AUTO)
    private Integer storageId;

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
     * 来源类型
     */
    private String sourceType;

    /**
     * 状态
     */
    private String status;

    /**
     * 来源单位
     */
    private String sourceCompany;

    /**
     * 入库时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date storageTime;

    /**
     * 入库负责人id
     */
    private Integer personnelId;

    /**
     * 入库负责人
     */
    private String personnelName;

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
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date auditTime;

    /**
     * 0：产品入库，1：其他出库
     */
    private String inoutType;
    /**
     * 出库类型
     */
    private String outboundType;
    /**
     * 出库日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date outboundTime;

    public void setStorageId(Integer storageId) {
        this.storageId = storageId;
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

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSourceCompany(String sourceCompany) {
        this.sourceCompany = sourceCompany;
    }

    public void setStorageTime(Date storageTime) {
        this.storageTime = storageTime;
    }

    public void setPersonnelId(Integer personnelId) {
        this.personnelId = personnelId;
    }

    public void setPersonnelName(String personnelName) {
        this.personnelName = personnelName;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setAuditBy(String auditBy) {
        this.auditBy = auditBy;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public void setInoutType(String inoutType) {
        this.inoutType = inoutType;
    }

    public void setOutboundType(String outboundType) {
        this.outboundType = outboundType;
    }

    public void setOutboundTime(Date outboundTime) {
        this.outboundTime = outboundTime;
    }
}
