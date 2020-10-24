package com.dc.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.exception.ServiceException;
import com.dc.project.system.dao.SysRoleMenuDao;
import com.dc.project.system.entity.SysRole;
import com.dc.project.system.entity.SysRoleMenu;
import com.dc.project.system.service.ISysRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 角色和菜单关联表 服务实现类
 *
 * @author zhuangchongyi
 * @since 2020-08-31
 */
@Slf4j
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenu> implements ISysRoleMenuService {

    @Transactional
    @Override
    public int addRoleMenu(SysRole sysRole) {
        if (null == sysRole.getRoleId()) {
            throw new ServiceException("菜单修改失败");
        }
        QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<SysRoleMenu>().eq("role_id", sysRole.getRoleId());
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            log.info("delete menuId=", Arrays.toString(sysRole.getMenuIds()));
            baseMapper.delete(queryWrapper);
        }
        Integer[] menuIds = sysRole.getMenuIds();
        int row = 0;
        int length = menuIds.length;
        for (int i = 0; i < length; i++) {
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setRoleId(sysRole.getRoleId());
            roleMenu.setMenuId(menuIds[i]);
            row = baseMapper.insert(roleMenu);
            if (row == 0) {
                throw new ServiceException("菜单修改失败");
            }
        }
        return row;
    }

    @Override
    public List<Integer> findRoleMenuByRoleIds(Integer[] roleIds) {
        if (roleIds.length == 0) return new ArrayList<>();
        return baseMapper.findRoleMenuByRoleIds(roleIds);
    }

}
