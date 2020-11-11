package com.dc.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.constant.CustomConstant;
import com.dc.common.exception.ServiceException;
import com.dc.common.vo.TreeSelect;
import com.dc.project.system.dao.SysRoleDao;
import com.dc.project.system.entity.*;
import com.dc.project.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 角色表 服务实现类
 *
 * @author zhuangchongyi
 * @since 2020-08-29
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements ISysRoleService {
    @Autowired
    private ISysUserRoleService userRoleService;
    @Autowired
    private ISysRoleMenuService roleMenuService;
    @Autowired
    private ISysRoleDeptService roleDeptService;
    @Autowired
    private ISysDeptService deptService;

    @Override
    public IPage<SysRole> list(Page page, SysRole sysRole) {
        return baseMapper.rolePage(page, sysRole);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean update(SysRole sysRole) {
        SysRole res = this.getOne(new QueryWrapper<SysRole>().select("role_id").eq("role_num", sysRole.getRoleNum()), false);
        Integer roleId = sysRole.getRoleId();
        if (null != res && !res.getRoleId().equals(roleId)) throw new ServiceException("该角色编码重复");
        if (roleId == null || !this.updateById(sysRole)) {
            throw new ServiceException();
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Integer roleId) {
        SysUserRole one = userRoleService.getOne(new QueryWrapper<SysUserRole>().eq("role_id", roleId), false);
        if (one != null) throw new ServiceException("该角色已有关联用户，不允许删除");
        //删除角色关联菜单
        roleMenuService.remove(new QueryWrapper<SysRoleMenu>().eq("role_id", roleId));
        // 删除角色关联部门
        roleDeptService.remove(new QueryWrapper<SysRoleDept>().eq("role_id", roleId));
        //删除角色
        this.removeById(roleId);
        return true;
    }

    @Override
    public Page<SysUser> roleUserListPage(Page<SysUser> page, Integer roleId) {
        return userRoleService.roleUserListPage(page, roleId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addRoleMenu(SysRole sysRole) {
        return roleMenuService.addRoleMenu(sysRole);
    }


    @Override
    public Map<String, Object> getDataScope(Integer id) {
        List<TreeSelect> dept = deptService.treeselect();
        QueryWrapper<SysRoleDept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", id)
                .notInSql("dept_id", "select distinct parent_id from sys_dept");
        List<SysRoleDept> list = roleDeptService.list(queryWrapper);
        List<Integer> ids = list.stream().map(rd -> rd.getDeptId()).collect(Collectors.toList());
        Map<String, Object> map = new HashMap<>();
        map.put("dept", dept);
        map.put("deptIds", ids);
        return map;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateDataScope(SysRole sysRole) {
        SysRoleDept one = roleDeptService.getOne(new QueryWrapper<SysRoleDept>().eq("role_id", sysRole.getRoleId()), false);
        if (null != one)
            roleDeptService.remove(new QueryWrapper<SysRoleDept>().eq("role_id", sysRole.getRoleId()));
        if (CustomConstant.DATA_SCOPE_CUSTOM.equals(sysRole.getDataScope())) {
            Integer[] deptIds = sysRole.getDeptIds();
            if (deptIds.length == 0) {
                throw new ServiceException("未添加部门");
            }
            List<SysRoleDept> list = Stream.of(deptIds)
                    .map(id -> new SysRoleDept().setDeptId(id).setRoleId(sysRole.getRoleId()))
                    .collect(Collectors.toList());
            if (!roleDeptService.saveBatch(list)) throw new ServiceException("修改数据权限失败");
        }
        if (!this.updateById(sysRole)) {
            throw new ServiceException();
        }
        return true;
    }

}
