package com.dc.project.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.lang.annotation.RepeatSubmit;
import com.dc.common.vo.R;
import com.dc.project.system.entity.SysUser;
import com.dc.project.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 人员表 前端控制器
 *
 * @author zhuangchongyi
 * @since 2020-08-28
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController {
    @Autowired
    private ISysUserService userService;

    @RequiresPermissions(value = {"system:user:list", "system:dept:user", "basis:personnel:list"}, logical = Logical.OR)
    @GetMapping
    public R page(Page<SysUser> page, SysUser user) {
        return R.success().data(userService.list(page, user));
    }

    @GetMapping("/list")
    public R list(Page<SysUser> page, SysUser user) {
        return R.success().data(userService.list(page, user));
    }

    @RepeatSubmit
    @RequiresPermissions("basis:personnel:add")
    @PostMapping
    public R save(@RequestBody @Validated SysUser sysUser) {
        return R.success().data(userService.insert(sysUser));
    }

    @GetMapping("/{userId}")
    public R get(@PathVariable(value = "userId", required = false) Integer userId) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(SysUser.class,
                info -> !info.getColumn().equals("create_time") && !info.getColumn().equals("create_by"))
                .eq("user_id", userId);
        return R.success().data(userService.getOne(queryWrapper));
    }

    @RepeatSubmit
    @RequiresPermissions(value = {"basis:personnel:edit"})
    @PutMapping
    public R update(@RequestBody @Validated SysUser sysUser) {
        return R.success().data(userService.update(sysUser));
    }

    @RepeatSubmit
    @RequiresPermissions(value = {"basis:personnel:delete"})
    @DeleteMapping("/{userId}")
    public R delete(@PathVariable(value = "userId", required = false) Integer userId) {
        return R.success().data(userService.removeById(userId));
    }

    /**
     * 修改用户状态
     *
     * @param sysUser
     * @return
     */
    @RepeatSubmit
    @RequiresPermissions(value = {"system:user:delete", "system:user:edit"}, logical = Logical.OR)
    @PutMapping("/status")
    public R status(@RequestBody SysUser sysUser) {
        return R.success().data(userService.updateStatus(sysUser));
    }

    /**
     * 重置密码
     *
     * @param userId
     * @return
     */
    @RepeatSubmit
    @RequiresPermissions("system:user:reset")
    @PutMapping("/resetPassword")
    public R resetPassword(@RequestBody Integer userId) {
        return R.success().data(userService.resetPassword(userId));
    }

    /**
     * 查询用户角色
     *
     * @return
     */
    @GetMapping(value = {"/userRoleList", "/userRoleList/{userId}"})
    public R userRoleList(@PathVariable(value = "userId", required = false) Integer userId) {
        return R.success().data(userService.findUserRoleByUserId(userId));
    }

    /**
     * 查询用户角色菜单权限
     *
     * @return
     */
    @RequiresPermissions("system:user:permission")
    @GetMapping("/userRoleMenuList/{userId}")
    public R userRoleMenuList(@PathVariable(value = "userId", required = false) Integer userId) {
        return R.success().data(userService.findUserRoleMenuByUserId(userId));
    }

    /**
     * 分配角色
     *
     * @param sysUser
     * @return
     */
    @RepeatSubmit
    @RequiresPermissions("system:user:role")
    @PostMapping("/addUserRole")
    public R addUserRole(@RequestBody SysUser sysUser) {
        return R.success().data(userService.addUserRole(sysUser));
    }

    /**
     * 修改密码
     *
     * @return
     */
    @RepeatSubmit
    @PutMapping("/changePassword")
    public R changePassword(@RequestBody Map<String, Object> formMap) {
        return R.success().data(userService.changePassword(formMap));
    }

}

