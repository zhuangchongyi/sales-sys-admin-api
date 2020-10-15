package com.dc.project.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dc.common.vo.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 角色表
 *
 * @author zhuangchongyi
 * @since 2020-08-29
 */
@Data
@Accessors(chain = true)
public class SysRole extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;

    /**
     * 角色名称
     */
    @NotNull(message = "角色名称不能为空")
    private String roleName;

    /**
     * 角色编码
     */
    @NotNull(message = "角色编码不能为空")
    @Length(max = 10, message = "角色编码不能超过10位")
    private String roleNum;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    private String dataScope;
    /**
     * 角色状态（0正常 1停用）
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 菜单权限
     */
    @TableField(exist = false)
    private List<SysMenu> menus;
    /**
     * 菜单组
     */
    @TableField(exist = false)
    private Integer[] menuIds;
}
