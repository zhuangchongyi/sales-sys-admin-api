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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 角色和菜单关联表 服务实现类
 *
 * @author zhuangchongyi
 * @since 2020-08-31
 */
@Slf4j
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenu> implements ISysRoleMenuService {

    @Override
    public boolean addRoleMenu(SysRole sysRole) {
        long begin = System.currentTimeMillis();
        Integer roleId = sysRole.getRoleId();
        if (null == roleId) {
            throw new ServiceException("菜单修改失败");
        }
        SysRoleMenu one = this.getOne(new QueryWrapper<SysRoleMenu>().eq("role_id", roleId), false);
        if (null != one) {
            log.info("delete menuIds");
            this.remove(new QueryWrapper<SysRoleMenu>().eq("role_id", roleId));
        }
        List<SysRoleMenu> list = Stream.of(sysRole.getMenuIds())
                .filter(id -> id != null)
                .map(id -> new SysRoleMenu().setRoleId(roleId).setMenuId(id))
                .collect(Collectors.toList());
        if (!list.isEmpty() && !this.saveBatch(list)) {
            throw new ServiceException("菜单修改失败");
        }
        long l = System.currentTimeMillis() - begin;
        System.out.println("耗时===" + l);
        return true;
    }

    @Override
    public List<Integer> findRoleMenuByRoleIds(Integer... roleIds) {
        return baseMapper.findRoleMenuByRoleIds(roleIds);
    }

}
