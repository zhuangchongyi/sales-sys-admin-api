package com.dc.project.warehouse.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dc.common.vo.R;
import com.dc.project.warehouse.entity.SysInventory;
import com.dc.project.warehouse.entity.SysInventorySub;
import com.dc.project.warehouse.service.ISysInventorySubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 盘点单子表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-09-23
 */
@RestController
@RequestMapping("/warehouse/inventorySub")
public class SysInventorySubController {
    @Autowired
    private ISysInventorySubService inventorySubService;

    @GetMapping
    public R list(SysInventory inventory) {
        QueryWrapper<SysInventorySub> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("inventory_id", inventory.getInventoryId())
                .orderByDesc("create_time");
        return R.success().data(inventorySubService.list(queryWrapper));
    }

}

