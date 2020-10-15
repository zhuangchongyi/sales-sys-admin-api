package com.dc.project.warehouse.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dc.common.vo.R;
import com.dc.project.warehouse.entity.SysWarehouseInit;
import com.dc.project.warehouse.entity.SysWarehouseInitSub;
import com.dc.project.warehouse.service.ISysWarehouseInitSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * 仓库初始化子表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-09-21
 */
@RestController
@RequestMapping("/warehouse/initSub")
public class SysWarehouseInitSubController {
    @Autowired
    private ISysWarehouseInitSubService warehouseInitSubService;

    @GetMapping
    public R list(SysWarehouseInit warehouseInit) {
        QueryWrapper<SysWarehouseInitSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("warehouse_id", warehouseInit.getWarehouseId());
        queryWrapper.orderByDesc("create_time");
        return R.success().data(warehouseInitSubService.list(queryWrapper));
    }

}

