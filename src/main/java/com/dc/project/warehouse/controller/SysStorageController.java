package com.dc.project.warehouse.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.vo.R;
import com.dc.project.sales.entity.SysOrder;
import com.dc.project.warehouse.entity.SysStorage;
import com.dc.project.warehouse.service.ISysStorageService;
import org.apache.commons.lang3.StringUtils;
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


    @GetMapping
    public R page(Page page, SysStorage sysStorage) {
        QueryWrapper<SysStorage> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(sysStorage.getWarehouseNum()), "warehouse_num", sysStorage.getWarehouseNum())
                .or().like(StringUtils.isNotEmpty(sysStorage.getWarehouseNum()), "warehouse_name", sysStorage.getWarehouseName())
                .orderByDesc("create_time");
        return R.success().data(storageService.page(page, queryWrapper));
    }

    @PostMapping
    public R addAndUpdate(@RequestBody Map formMap) throws Exception {
        return R.success().data(storageService.addAndUpdate(formMap));
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        return R.success().data(storageService.delete(id));
    }

    @GetMapping("/{id}")
    public R get(@PathVariable Integer id) {
        return R.success().data(storageService.getById(id));
    }

    @PutMapping("/submit/{status}")
    public R submit(@RequestBody Integer[] ids, @PathVariable String status) {
        return R.success().data(storageService.submit(ids, status));
    }

    @PutMapping("/audit")
    public R audit(@RequestBody SysStorage storage) {
        return R.success().data(storageService.audit(storage));
    }
}

