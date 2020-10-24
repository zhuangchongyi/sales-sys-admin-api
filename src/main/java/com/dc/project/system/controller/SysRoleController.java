package com.dc.project.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.constant.CustomConstant;
import com.dc.common.vo.R;
import com.dc.common.exception.ServiceException;
import com.dc.project.system.entity.SysRole;
import com.dc.project.system.entity.SysRoleMenu;
import com.dc.project.system.entity.SysUserRole;
import com.dc.project.system.service.ISysRoleMenuService;
import com.dc.project.system.service.ISysRoleService;
import com.dc.project.system.service.ISysUserRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 角色表 前端控制器
 *
 * @author zhuangchongyi
 * @since 2020-08-29
 */
@RestController
@RequestMapping("/system/role")
public class SysRoleController {
    @Autowired
    private ISysRoleService roleService;
    @Autowired
    private ISysUserRoleService userRoleService;
    @Autowired
    private ISysRoleMenuService roleMenuService;

    @RequiresPermissions("system:role:list")
    @GetMapping
    public R list(int current, int size, SysRole sysRole) {
        return R.success().data(roleService.list(new Page<>(current, size), sysRole));
    }

    @RequiresPermissions("system:role:add")
    @PostMapping
    public R save(@RequestBody @Validated SysRole sysRole) {
        int count = roleService.count(new QueryWrapper<SysRole>().select("role_id").eq("role_num", sysRole.getRoleNum()));
        if (count > 0)
            throw new ServiceException("该角色编码重复");
        return R.success().data(roleService.save(sysRole));
    }

    @GetMapping("/{roleId}")
    public R get(@PathVariable Integer roleId) {
        return R.success().data(roleService.getOne(
                new QueryWrapper<SysRole>().select("role_id, role_name, role_num, data_scope, status, remark").eq("role_id", roleId)));
    }

    @RequiresPermissions("system:role:edit")
    @PutMapping
    public R update(@RequestBody @Validated SysRole sysRole) {
        SysRole res = roleService.getOne(new QueryWrapper<SysRole>().select("role_id").eq("role_num", sysRole.getRoleNum()));
        if (null != res && !res.getRoleId().equals(sysRole.getRoleId()))
            throw new ServiceException("该角色编码重复");
        if (CustomConstant.STOP_STATUS.equals(res.getStatus())) {
            SysUserRole userRole = userRoleService.getOne(new QueryWrapper<SysUserRole>().eq("role_id", res.getRoleId()));
            if (null != userRole) {
                throw new ServiceException("该角色已被使用，无法停用");
            }
        }
        return R.success().data(roleService.updateById(sysRole));
    }

    @RequiresPermissions("system:role:delete")
    @DeleteMapping("/{roleId}")
    public R delete(@PathVariable Integer roleId) {
        int count = userRoleService.count(new QueryWrapper<SysUserRole>().eq("role_id", roleId));
        if (count > 0)
            throw new ServiceException("该角色已关联用户，不允许删除");
        roleMenuService.remove(new QueryWrapper<SysRoleMenu>().eq("role_id", roleId));
        return R.success().data(roleService.removeById(roleId));
    }

    /**
     * 查询该角色的所有用户
     *
     * @param roleId
     * @param current
     * @param size
     * @return
     */
    @RequiresPermissions("system:user:role")
    @GetMapping("/roleUserList")
    public R roleUserListPage(Integer roleId, int current, int size) {
        return R.success().data(userRoleService.roleUserListPage(new Page<>(current, size), roleId));
    }

    /**
     * 修改菜单权限
     *
     * @param sysRole
     * @return
     */
    @RequiresPermissions("system:role:permission")
    @PostMapping("/addRoleMenu")
    public R addRoleMenu(@RequestBody SysRole sysRole) {
        return R.success().data(roleMenuService.addRoleMenu(sysRole));
    }

}

