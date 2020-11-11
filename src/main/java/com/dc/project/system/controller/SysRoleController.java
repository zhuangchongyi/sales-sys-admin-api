package com.dc.project.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.lang.annotation.RepeatSubmit;
import com.dc.common.exception.ServiceException;
import com.dc.common.vo.R;
import com.dc.project.system.entity.SysRole;
import com.dc.project.system.service.ISysRoleService;
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

    @RequiresPermissions("system:role:list")
    @GetMapping
    public R list(int current, int size, SysRole sysRole) {
        return R.success().data(roleService.list(new Page<>(current, size), sysRole));
    }

    @RepeatSubmit
    @RequiresPermissions("system:role:add")
    @PostMapping
    public R save(@RequestBody @Validated SysRole sysRole) {
        SysRole count = roleService.getOne(new QueryWrapper<SysRole>().select("role_id").eq("role_num", sysRole.getRoleNum()), false);
        if (null != count) throw new ServiceException("该角色编码重复");
        return R.success().data(roleService.save(sysRole));
    }

    @GetMapping("/{roleId}")
    public R get(@PathVariable Integer roleId) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(SysRole.class,
                info -> !info.getColumn().equals("create_time") && !info.getColumn().equals("create_by"))
                .eq("role_id", roleId);
        return R.success().data(roleService.getOne(queryWrapper));
    }

    @RepeatSubmit
    @RequiresPermissions("system:role:edit")
    @PutMapping
    public R update(@RequestBody @Validated SysRole sysRole) {
        return R.success().data(roleService.update(sysRole));
    }


    @RepeatSubmit
    @RequiresPermissions("system:role:delete")
    @DeleteMapping("/{roleId}")
    public R delete(@PathVariable Integer roleId) {
        return R.success().data(roleService.delete(roleId));
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
        return R.success().data(roleService.roleUserListPage(new Page<>(current, size), roleId));
    }

    /**
     * 修改菜单权限
     *
     * @param sysRole
     * @return
     */
    @RepeatSubmit
    @RequiresPermissions("system:role:permission")
    @PostMapping("/addRoleMenu")
    public R addRoleMenu(@RequestBody SysRole sysRole) {
        return R.success().data(roleService.addRoleMenu(sysRole));
    }

    /**
     * 获取角色关联部门id
     *
     * @param id
     * @return
     */
    @GetMapping("/dataScope/{id}")
    public R getDataScope(@PathVariable Integer id) {
        return R.success().data(roleService.getDataScope(id));
    }

    /**
     * 新增角色部门
     *
     * @param sysRole
     * @return
     */
    @RepeatSubmit
    @RequiresPermissions("system:role:dataScope")
    @PostMapping("/dataScope")
    public R addDataScope(@RequestBody SysRole sysRole) {
        return R.success().data(roleService.updateDataScope(sysRole));
    }

}

