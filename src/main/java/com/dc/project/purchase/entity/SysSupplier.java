package com.dc.project.purchase.entity;

import com.dc.common.valid.InsertGroup;
import com.dc.common.valid.UpdateGroup;
import com.dc.common.vo.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 供应商表
 *
 * @author zhuangcy
 * @since 2020-11-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class SysSupplier extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "supplier_id", type = IdType.AUTO)
    @NotNull(message = "供应商编码不能为空", groups = {UpdateGroup.class})
    private Integer supplierId;

    /**
     * 供应商编码
     */
    @NotNull(message = "供应商编码不能为空", groups = {InsertGroup.class, UpdateGroup.class})
    private String supplierNum;

    /**
     * 供应商名称
     */
    private String supplierName;

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
     * 业务员id
     */
    private Integer personnelId;

    /**
     * 业务员名称
     */
    private String personnelName;

    /**
     * 启用状态（0正常 1停用）
     */
    private String status;

    /**
     * 客户id
     */
    private Integer clienteleId;

    /**
     * 备注
     */
    private String remark;


}
