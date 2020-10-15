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
 * 盘点单主表
 *
 * @author zhuangcy
 * @since 2020-09-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysInventory extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "inventory_id", type = IdType.AUTO)
    private Integer inventoryId;

    /**
     * 调整单id
     */
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
     * 盘点时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date inventoryTime;

    /**
     * 盘点负责人id
     */
    private Integer personnelId;

    /**
     * 盘点负责人
     */
    private String personnelName;

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

    /**
     * 备注
     */
    private String remark;


}
