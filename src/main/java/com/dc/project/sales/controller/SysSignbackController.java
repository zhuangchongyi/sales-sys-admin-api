package com.dc.project.sales.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.vo.R;
import com.dc.project.sales.entity.SysSignback;
import com.dc.project.sales.service.ISysSignbackService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 销售签回主表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-10-10
 */
@RestController
@RequestMapping("/sales/signback")
public class SysSignbackController {
    @Autowired
    private ISysSignbackService signbackService;

    @RequiresPermissions(value = {"sales:signback:list"}, logical = Logical.OR)
    @GetMapping
    public R page(Page page, SysSignback sysSignback) {
        return R.success().data(signbackService.page(page, sysSignback));
    }

    @GetMapping("/{id}")
    public R get(@PathVariable Integer id) {
        return R.success().data(signbackService.get(id));
    }

    @RequiresPermissions(value = {"sales:signback:edit"})
    @PutMapping
    public R edit(@RequestBody Map<String, Object> formMap) throws InvocationTargetException, IllegalAccessException {
        return R.success().data(signbackService.edit(formMap));
    }

    @RequiresPermissions(value = {"sales:signback:audit"})
    @PutMapping("/audit")
    public R audit(@RequestBody SysSignback sysSignback) {
        return R.success().data(signbackService.audit(sysSignback));
    }

    @GetMapping("/clientele")
    public R findOrderByClienteleId(Page page, SysSignback sysSignback) {
        return R.success().data(signbackService.findOrderByClienteleId(page, sysSignback));
    }

    @GetMapping("/finance")
    public R findFinanceOrder(Page page, SysSignback sysSignback) {
        return R.success().data(signbackService.findFinanceOrder(page, sysSignback));
    }

}

