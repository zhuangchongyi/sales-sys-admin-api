package com.dc.project.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.common.vo.TreeSelect;
import com.dc.common.vo.RouterVo;
import com.dc.project.system.entity.SysMenu;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 菜单权限表 服务类
 *
 * @author zhuangchongyi
 * @since 2020-08-29
 */
public interface ISysMenuService extends IService<SysMenu> {

    Map findMenuByRoleId(Integer roleId);

    List<TreeSelect> buildMenuTreeSelect(List<SysMenu> menus);

    List<RouterVo> getRouters();

    Set<String> getMenuPermission(Integer userId);
}
