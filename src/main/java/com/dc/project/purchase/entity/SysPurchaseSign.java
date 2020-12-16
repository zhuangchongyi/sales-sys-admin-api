package com.dc.project.purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 采购到货主表
 *
 * @author zhuangcy
 * @since 2020-12-05
 */
@Data
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
    private Date signTime;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 订单单号
     */
    private String orderNum;

    /**
     * 订单日期
     */
    private Date orderTime;

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
    private Date auditTime;

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
     * 入库时间
     */
    private Date storageTime;

    /**
     * 入库状态（1表示入库）
     */
    private Integer storageStatus;

    /**
     * 入库审核状态
     */
    private String auditStatus;

}
