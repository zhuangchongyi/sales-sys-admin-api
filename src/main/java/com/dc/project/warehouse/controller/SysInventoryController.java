package com.dc.project.warehouse.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.vo.R;
import com.dc.project.warehouse.entity.SysInventory;
import com.dc.project.warehouse.service.ISysInventoryService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 盘点单主表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-09-23
 */
@RestController
@RequestMapping("/warehouse/inventory")
public class SysInventoryController {

    @Autowired
    private ISysInventoryService inventoryService;

    @RequiresPermissions(value = {"warehouse:inventory:list"})
    @GetMapping
    public R page(Page page, SysInventory inventory) {
        QueryWrapper<SysInventory> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(inventory.getWarehouseName()), "warehouse_name", inventory.getWarehouseName())
                .or().like(StringUtils.isNotEmpty(inventory.getWarehouseNum()), "warehouse_num", inventory.getWarehouseNum())
                .orderByDesc("create_time");
        return R.success().data(inventoryService.page(page, queryWrapper));
    }

    @RequiresPermissions(value = {"warehouse:inventory:add", "warehouse:inventory:edit"}, logical = Logical.OR)
    @PostMapping
    public R addAndUpdate(@RequestBody Map formMap) throws Exception {
        return R.success().data(inventoryService.addAndUpdate(formMap));
    }

    @RequiresPermissions(value = {"warehouse:inventory:delete"})
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        return R.success().data(inventoryService.delete(id));
    }

    @GetMapping("/{id}")
    public R get(@PathVariable Integer id) {
        return R.success().data(inventoryService.getById(id));
    }

    @RequiresPermissions(value = {"warehouse:inventory:submit"})
    @PutMapping("/submit/{status}")
    public R submit(@RequestBody Integer[] ids, @PathVariable String status) {
        return R.success().data(inventoryService.submit(ids, status));
    }

    @RequiresPermissions(value = {"warehouse:inventory:audit"})
    @PutMapping("/audit")
    public R audit(@RequestBody SysInventory inventory) {
        return R.success().data(inventoryService.audit(inventory));
    }
}

