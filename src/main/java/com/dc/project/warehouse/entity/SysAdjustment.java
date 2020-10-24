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
 * 调整单主表
 *
 * @author zhuangcy
 * @since 2020-09-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysAdjustment extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "adjustment_id", type = IdType.AUTO)
    private Integer adjustmentId;

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
     * 调整类型(0新增调整，1盘点调整)
     */
    private String adjustmentType;

    /**
     * 调整时间
     */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date adjustmentTime;

    /**
     * 调整负责人
     */
    private String personnelName;

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
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date auditTime;

    public void setAuditBy(String auditBy) {
        this.auditBy = auditBy;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public void setAdjustmentId(Integer adjustmentId) {
        this.adjustmentId = adjustmentId;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public void setAdjustmentTime(Date adjustmentTime) {
        this.adjustmentTime = adjustmentTime;
    }

    public void setPersonnelName(String personnelName) {
        this.personnelName = personnelName;
    }

    public void setPersonnelId(Integer personnelId) {
        this.personnelId = personnelId;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
