package com.dc.project.warehouse.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.vo.R;
import com.dc.project.warehouse.entity.SysStorage;
import com.dc.project.warehouse.service.ISysStorageService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 产品入库主表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-09-22
 */
@RestController
@RequestMapping("/warehouse/storage")
public class SysStorageController {
    @Autowired
    private ISysStorageService storageService;


    @RequiresPermissions(value = {"warehouse:storage:list"}, logical = Logical.OR)
    @GetMapping
    public R page(Page<SysStorage> page, SysStorage sysStorage) {
        return R.success().data(storageService.page(page, sysStorage));
    }

    @RequiresPermissions(value = {"warehouse:storage:add", "warehouse:storage:edit"}, logical = Logical.OR)
    @PostMapping
    public R addAndUpdate(@RequestBody Map formMap) throws Exception {
        return R.success().data(storageService.addAndUpdate(formMap));
    }

    @RequiresPermissions(value = {"warehouse:storage:delete"}, logical = Logical.OR)
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        return R.success().data(storageService.delete(id));
    }

    @GetMapping("/{id}")
    public R get(@PathVariable Integer id) {
        return R.success().data(storageService.getById(id));
    }

    @RequiresPermissions(value = {"warehouse:storage:submit"}, logical = Logical.OR)
    @PutMapping("/submit/{status}")
    public R submit(@RequestBody Integer[] ids, @PathVariable String status) {
        return R.success().data(storageService.submit(ids, status));
    }

    @RequiresPermissions(value = {"warehouse:storage:audit"}, logical = Logical.OR)
    @PutMapping("/audit")
    public R audit(@RequestBody SysStorage storage) {
        return R.success().data(storageService.audit(storage));
    }
}

