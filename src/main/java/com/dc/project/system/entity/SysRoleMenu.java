package com.dc.project.system.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色和菜单关联表
 *
 * @author zhuangchongyi
 * @since 2020-08-31
 */
@Data
@Accessors(chain = true)
public class SysRoleMenu {

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 菜单ID
     */
    private Integer menuId;
}
