package com.dc.project.basis.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.lang.annotation.RepeatSubmit;
import com.dc.common.exception.ServiceException;
import com.dc.common.vo.R;
import com.dc.project.basis.entity.SysWarehouse;
import com.dc.project.basis.service.ISysWarehouseService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 仓库表 前端控制器
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
@RestController
@RequestMapping("/basis/warehouse")
public class SysWarehouseController {
    @Autowired
    private ISysWarehouseService warehouseService;

    @RequiresPermissions("basis:warehouse:list")
    @GetMapping
    public R page(Page<SysWarehouse> page, SysWarehouse warehouse) {
        return R.success().data(warehouseService.list(page, warehouse));
    }

    /**
     * 弹窗列表查询
     *
     * @param page
     * @param warehouse
     * @return
     */
    @GetMapping("/list")
    public R list(Page<SysWarehouse> page, SysWarehouse warehouse) {
        QueryWrapper<SysWarehouse> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(warehouse.getWarehouseNum()), "warehouse_num", warehouse.getWarehouseNum())
                .or().like(StringUtils.isNotEmpty(warehouse.getWarehouseName()), "warehouse_name", warehouse.getWarehouseName());
        return R.success().data(warehouseService.page(page, queryWrapper));
    }

    @GetMapping("/{warehouseId}")
    public R get(@PathVariable Integer warehouseId) {
        return R.success().data(warehouseService.getByWarehouseId(warehouseId));
    }

    @RepeatSubmit
    @RequiresPermissions("basis:warehouse:add")
    @PostMapping
    public R add(@RequestBody @Validated SysWarehouse warehouse) {
        SysWarehouse count = warehouseService.getOne(new QueryWrapper<SysWarehouse>().select("warehouse_id").eq("warehouse_num", warehouse.getWarehouseNum()));
        if (null != count)
            throw new ServiceException("编码不允许重复");
        return R.success().data(warehouseService.save(warehouse));
    }

    @RepeatSubmit
    @RequiresPermissions("basis:warehouse:edit")
    @PutMapping
    public R update(@RequestBody @Validated SysWarehouse warehouse) {
        SysWarehouse res = warehouseService.getOne(new QueryWrapper<SysWarehouse>().select("warehouse_id").eq("warehouse_num", warehouse.getWarehouseNum()));
        if (null != res && !res.getWarehouseId().equals(warehouse.getWarehouseId()))
            throw new ServiceException("编码不允许重复");
        return R.success().data(warehouseService.updateById(warehouse));
    }

    @RepeatSubmit
    @RequiresPermissions("basis:warehouse:delete")
    @DeleteMapping("/{warehouseId}")
    public R delete(@PathVariable Integer warehouseId) {
        return R.success().data(warehouseService.removeById(warehouseId));
    }

    @GetMapping("/init")
    public R init(Page<SysWarehouse> page, SysWarehouse warehouse) {
        return R.success().data(warehouseService.initPage(page, warehouse));
    }

}

