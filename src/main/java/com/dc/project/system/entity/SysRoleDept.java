package com.dc.project.system.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色和部门关联表
 *
 * @author zhuangcy
 * @since 2020-11-07
 */
@Data
@Accessors(chain = true)
public class SysRoleDept {

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 部门ID
     */
    private Integer deptId;

}
