package com.dc.project.warehouse.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.vo.R;
import com.dc.project.warehouse.entity.SysAdjustment;
import com.dc.project.warehouse.service.ISysAdjustmentService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 调整单主表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-09-23
 */
@RestController
@RequestMapping("/warehouse/adjustment")
public class SysAdjustmentController {
    @Autowired
    private ISysAdjustmentService adjustmentService;

    @RequiresPermissions(value = {"warehouse:adjustment:list"})
    @GetMapping
    public R page(Page<SysAdjustment> page, SysAdjustment adjustment) {
        return R.success().data(adjustmentService.page(page, adjustment));
    }

    @RequiresPermissions(value = {"warehouse:adjustment:add", "warehouse:adjustment:edit"}, logical = Logical.OR)
    @PostMapping
    public R addAndUpdate(@RequestBody Map formMap) throws Exception {
        return R.success().data(adjustmentService.addAndUpdate(formMap));
    }

    @RequiresPermissions(value = {"warehouse:adjustment:delete"})
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        return R.success().data(adjustmentService.delete(id));
    }

    @GetMapping("/{id}")
    public R get(@PathVariable Integer id) {
        return R.success().data(adjustmentService.getById(id));
    }

    @RequiresPermissions(value = {"warehouse:adjustment:submit"})
    @PutMapping("/submit/{status}")
    public R submit(@RequestBody Integer[] ids, @PathVariable String status) {
        return R.success().data(adjustmentService.submit(ids, status));
    }

    @RequiresPermissions(value = {"warehouse:adjustment:audit"})
    @PutMapping("/audit")
    public R audit(@RequestBody SysAdjustment adjustment) {
        return R.success().data(adjustmentService.audit(adjustment));
    }
}

