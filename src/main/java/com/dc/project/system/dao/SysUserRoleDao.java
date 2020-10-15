package com.dc.project.system.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.system.entity.SysUser;
import com.dc.project.system.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 用户和角色关联表 Mapper 接口
 *
 * @author zhuangchongyi
 * @since 2020-08-31
 */
public interface SysUserRoleDao extends BaseMapper<SysUserRole> {

    Page<SysUser> roleUserListPage(Page<SysUser> page, Integer roleId);

    List<Integer> findUserRoleByUserId(Integer userId);
}
