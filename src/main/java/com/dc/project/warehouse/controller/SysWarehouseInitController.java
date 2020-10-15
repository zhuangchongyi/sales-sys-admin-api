package com.dc.project.warehouse.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.vo.R;
import com.dc.project.warehouse.entity.SysWarehouseInit;
import com.dc.project.warehouse.service.ISysWarehouseInitService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 仓库初始化主表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-09-21
 */
@RestController
@RequestMapping("/warehouse/init")
public class SysWarehouseInitController {
    @Autowired
    private ISysWarehouseInitService warehouseInitService;


    @GetMapping
    public R page(Page page, SysWarehouseInit warehouseInit) {
        QueryWrapper<SysWarehouseInit> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(warehouseInit.getWarehouseNum()), "warehouse_num", warehouseInit.getWarehouseNum())
                .or().like(StringUtils.isNotEmpty(warehouseInit.getWarehouseName()), "warehouse_name", warehouseInit.getWarehouseName());
        queryWrapper.orderByDesc("create_time");
        return R.success().data(warehouseInitService.page(page, queryWrapper));
    }

    @PostMapping
    public R addAndUpdate(@RequestBody Map formMap) throws Exception {
        return R.success().data(warehouseInitService.addAndUpdate(formMap));
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        return R.success().data(warehouseInitService.delete(id));
    }

    @GetMapping("/{id}")
    public R get(@PathVariable Integer id) {
        return R.success().data(warehouseInitService.getById(id));
    }
}

