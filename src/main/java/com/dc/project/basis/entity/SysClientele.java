package com.dc.project.basis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;
import com.dc.project.system.entity.SysUser;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 部门表
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
@Data
@Accessors(chain = true)
public class SysClientele extends BaseEntity {

    /**
     * 客户id
     */
    @TableId(value = "clientele_id", type = IdType.AUTO)
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
     * 合作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date corporationTime;

    /**
     * 法人
     */
    private String legalPerson;

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
     * 公司简称
     */
    private String abbreviation;

    /**
     * 统一社会信用证号
     */
    private String certificateNum;

    /**
     * 等级
     */
    private String level;

    /**
     * 公司地址
     */
    private String address;

    /**
     * 业务员id
     */
    private Integer userId;

    /**
     * 启用状态（0正常 1停用）
     */
    private String status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @JsonIgnore
    private String delFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 客户类别
     */
    @TableField(exist = false)
    private SysCategory category;

    /**
     * 业务人员
     */
    @TableField(exist = false)
    private SysUser personnel;
}
