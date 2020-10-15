package com.dc.project.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.project.system.entity.SysUser;

import java.util.Map;

/**
 * 人员表 服务类
 *
 * @author zhuangchongyi
 * @since 2020-08-28
 */
public interface ISysUserService extends IService<SysUser> {

    SysUser findByUsername(String username);

    IPage<SysUser> list(Page page, SysUser user);

    String resetPassword(Integer userId);

    boolean update(SysUser sysUser);

    Map findUserRoleByUserId(Integer userId);

    Map findUserRoleMenuByUserId(Integer userId);

    boolean addUserRole(SysUser sysUser);

    boolean changePassword(Map<String, Object> formMap);

    SysUser findRoleByUsername(String username);

    boolean updateStatus(SysUser sysUser);

    SysUser getUserByUsername(String username);
}
