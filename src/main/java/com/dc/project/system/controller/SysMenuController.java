package com.dc.project.system.controller;

import com.dc.common.vo.R;
import com.dc.project.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhuangchongyi
 * @Description 菜单控制层
 * @Date 2020/8/28 14:30
 */
@RestController
@RequestMapping("/system/menu")
public class SysMenuController {
    @Autowired
    private ISysMenuService menuService;

    /**
     * 获取菜单路由
     *
     * @return
     */
    @GetMapping("/getRouters")
    public R getRouters() {
        return R.success().data(menuService.getRouters());
    }

    /**
     * 查询该角色的菜单权限
     *
     * @param roleId
     * @return
     */
    @GetMapping("/menuPermission/{roleId}")
    public R menuPermission(@PathVariable Integer roleId) {
        return R.success().data(menuService.findMenuByRoleId(roleId));
    }
}
