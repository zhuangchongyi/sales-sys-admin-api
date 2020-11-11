package com.dc.project.system.dao;

import com.dc.project.system.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 菜单权限表 Mapper 接口
 *
 * @author zhuangchongyi
 * @since 2020-08-29
 */
public interface SysMenuDao extends BaseMapper<SysMenu> {

    List<SysMenu> findMenuTreeAll();

    List<SysMenu> findMenuTreeByRoleIds(List<Integer> roleIds);

    List<String> getMenuPermission(Integer userId);
}
