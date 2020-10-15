package com.dc.project.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.project.system.entity.SysRole;
import com.dc.project.system.entity.SysRoleMenu;

import java.util.List;

/**
 * 角色和菜单关联表 服务类
 *
 * @author zhuangchongyi
 * @since 2020-08-31
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {

    int addRoleMenu(SysRole sysRole);

    List<Integer> findRoleMenuByRoleIds(Integer[] roleIds);

}
