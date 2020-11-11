package com.dc.project.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * 人员表
 *
 * @author zhuangchongyi
 * @since 2020-08-28
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode
public class SysUser extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 登录名称
     */
    private String username;

    /**
     * 登录密码
     */
    @JsonIgnore
    private String password;

    /**
     * 加密盐
     */
    @JsonIgnore
    private String salt;

    /**
     * 用户编码
     */
    @NotNull(message = "员工编码不能为空")
    @Length(max = 10, message = "员工编码长度不能超过10位")
    private String userNum;

    /**
     * 用户昵称
     */
    @NotNull(message = "员工名称不能为空")
    private String nickname;

    /**
     * 性别（0男 1女 2未知）
     */
    private String gender;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * qq号码
     */
    private String qq;

    /**
     * 身份证号码
     */
    private String identityNo;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 现住址
     */
    private String nowAddress;

    /**
     * 家庭住址
     */
    private String homeAddress;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 在职状态/0在职/1离职
     */
    private String jobStatus;

    /**
     * 用户类型/0普通用户/1管理员
     */
    private String userType;

    /**
     * 启用状态/0启用/1禁用
     */
    private String startStatus;

    /**
     * 入职时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date entryTime;

    /**
     * 离职时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date resignationTime;

    /**
     * 部门id
     */
    private Integer deptId;

    /**
     * 部门
     */
    @TableField(exist = false)
    private SysDept dept;

    /**
     * 角色
     */
    @TableField(exist = false)
    private List<SysRole> roles;

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
     * 角色组
     */
    @TableField(exist = false)
    private Integer[] roleIds;

}
