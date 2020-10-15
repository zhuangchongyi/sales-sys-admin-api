package com.dc.project.system.dao;

import com.dc.project.system.entity.SysRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 角色和菜单关联表 Mapper 接口
 *
 * @author zhuangchongyi
 * @since 2020-08-31
 */
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenu> {

    List<Integer> findRoleMenuByRoleIds(Integer[] roleIds);

}
