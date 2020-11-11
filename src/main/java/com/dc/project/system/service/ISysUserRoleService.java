package com.dc.project.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.project.system.entity.SysUser;
import com.dc.project.system.entity.SysUserRole;

import java.util.List;

/**
 * 用户和角色关联表 服务类
 *
 * @author zhuangchongyi
 * @since 2020-08-31
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    Page<SysUser> roleUserListPage(Page<SysUser> page, Integer roleId);

    List<Integer> userRoleList(Integer userId);

}
