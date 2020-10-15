package com.dc.project.system.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户和角色关联表
 *
 * @author zhuangchongyi
 * @since 2020-08-31
 */
@Data
@Accessors(chain = true)
public class SysUserRole {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 角色ID
     */
    private Integer roleId;
}
