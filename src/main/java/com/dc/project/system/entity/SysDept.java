package com.dc.project.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 部门档案表
 *
 * @author zhuangchongyi
 * @since 2020-08-29
 */
@Data
@Accessors(chain = true)
public class SysDept extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 部门id
     */
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Integer deptId;

    /**
     * 部门编码
     */
    private String deptNum;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 父部门id
     */
    private Integer parentId;

    /**
     * 部门类型（0部门 1公司）
     */
    private String deptType;

    /**
     * 负责人
     */
    private String leader;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 部门状态（0正常 1停用）
     */
    private String status;
    /**
     * 地址
     */
    private String address;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @JsonIgnore
    private String delFlag;

    /**
     * 子部门
     */
    @TableField(exist = false)
    private List<SysDept> children = new ArrayList<>();

}
