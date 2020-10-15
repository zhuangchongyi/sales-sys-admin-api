package com.dc.project.warehouse.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.vo.R;
import com.dc.project.sales.entity.SysOrder;
import com.dc.project.warehouse.entity.SysAdjustment;
import com.dc.project.warehouse.service.ISysAdjustmentService;
import org.apache.commons.lang3.StringUtils;
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

    @GetMapping
    public R page(Page page, SysAdjustment inventory) {
        QueryWrapper<SysAdjustment> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(inventory.getWarehouseName()), "warehouse_name", inventory.getWarehouseName())
                .or().like(StringUtils.isNotEmpty(inventory.getWarehouseNum()), "warehouse_num", inventory.getWarehouseNum())
                .orderByDesc("create_time");
        return R.success().data(adjustmentService.page(page, queryWrapper));
    }

    @PostMapping
    public R addAndUpdate(@RequestBody Map formMap) throws Exception {
        return R.success().data(adjustmentService.addAndUpdate(formMap));
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        return R.success().data(adjustmentService.delete(id));
    }

    @GetMapping("/{id}")
    public R get(@PathVariable Integer id) {
        return R.success().data(adjustmentService.getById(id));
    }

    @PutMapping("/submit/{status}")
    public R submit(@RequestBody Integer[] ids, @PathVariable String status) {
        return R.success().data(adjustmentService.submit(ids, status));
    }

    @PutMapping("/audit")
    public R audit(@RequestBody SysAdjustment adjustment) {
        return R.success().data(adjustmentService.audit(adjustment));
    }
}

