package com.dc.project.purchase.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.lang.annotation.RepeatSubmit;
import com.dc.common.vo.R;
import com.dc.project.purchase.entity.SysPurchaseSign;
import com.dc.project.purchase.service.ISysPurchaseSignService;
import com.dc.project.purchase.vo.PurchaseVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 采购到货主表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-12-05
 */
@RestController
@RequestMapping("/purchase/sign")
public class SysPurchaseSignController {

    @Autowired
    private ISysPurchaseSignService purchaseSignService;

    @RequiresPermissions("purchase:sign:list")
    @GetMapping
    public R page(Page<SysPurchaseSign> page, SysPurchaseSign sign) {
        return R.success().data(purchaseSignService.page(page, sign));
    }

    @GetMapping("/list")
    public R list(Page<SysPurchaseSign> page, SysPurchaseSign sign) {
        return R.success().data(purchaseSignService.page(page, sign));
    }

    @GetMapping("/{id}")
    public R get(@PathVariable Integer id) {
        return R.success().data(purchaseSignService.get(id));
    }

    @RequiresPermissions("purchase:sign:add")
    @RepeatSubmit
    @PostMapping
    public R add(@RequestBody PurchaseVo signVo) {
        return R.success().data(purchaseSignService.add(signVo));
    }

    @RequiresPermissions("purchase:sign:edit")
    @RepeatSubmit
    @PutMapping
    public R edit(@RequestBody PurchaseVo signVo) {
        return R.success().data(purchaseSignService.edit(signVo));
    }

    @RequiresPermissions("purchase:sign:delete")
    @RepeatSubmit
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        return R.success().data(purchaseSignService.delete(id));
    }

    @RequiresPermissions("purchase:sign:submit")
    @RepeatSubmit
    @PutMapping("/submit")
    public R submit(@RequestBody PurchaseVo signVo) {
        return R.success().data(purchaseSignService.submit(signVo));
    }

    @RequiresPermissions("purchase:sign:audit")
    @RepeatSubmit
    @PutMapping("/audit")
    public R audit(@RequestBody PurchaseVo signVo) {
        return R.success().data(purchaseSignService.audit(signVo));
    }

}

