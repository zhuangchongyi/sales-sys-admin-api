package com.dc.project.purchase.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.lang.annotation.RepeatSubmit;
import com.dc.common.vo.R;
import com.dc.project.purchase.entity.SysSupplier;
import com.dc.project.purchase.service.ISysSupplierService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 供应商表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-11-26
 */
@RestController
@RequestMapping("/purchase/supplier")
public class SysSupplierController {
    @Autowired
    private ISysSupplierService supplierService;

    @RequiresPermissions("purchase:supplier:list")
    @GetMapping
    public R page(Page<SysSupplier> page, SysSupplier supplier) {
        return R.success().data(supplierService.page(page, supplier));
    }

    @GetMapping("/list")
    public R list(Page<SysSupplier> page, SysSupplier supplier) {
        return R.success().data(supplierService.page(page, supplier));
    }

    @GetMapping("/{id}")
    public R get(@PathVariable Integer id) {
        return R.success().data(supplierService.get(id));
    }

    @RequiresPermissions("purchase:supplier:add")
    @RepeatSubmit
    @PostMapping
    public R add(@RequestBody @Validated SysSupplier supplier) {
        return R.success().data(supplierService.add(supplier));
    }

    @RequiresPermissions("purchase:supplier:edit")
    @RepeatSubmit
    @PutMapping
    public R edit(@RequestBody @Validated SysSupplier supplier) {
        return R.success().data(supplierService.edit(supplier));
    }

    @RequiresPermissions("purchase:supplier:delete")
    @RepeatSubmit
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        return R.success().data(supplierService.delete(id));
    }


}

