package com.dc.project.purchase.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.lang.annotation.RepeatSubmit;
import com.dc.common.valid.AuditGroup;
import com.dc.common.valid.InsertGroup;
import com.dc.common.valid.SubmitGroup;
import com.dc.common.valid.UpdateGroup;
import com.dc.common.vo.R;
import com.dc.project.purchase.entity.SysPurchaseSign;
import com.dc.project.purchase.entity.SysPurchaseSignSub;
import com.dc.project.purchase.service.ISysPurchaseSignService;
import com.dc.common.vo.CommonVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
    public R add(@RequestBody @Validated(InsertGroup.class) CommonVo<SysPurchaseSign, SysPurchaseSignSub> vo) {
        return R.success().data(purchaseSignService.add(vo));
    }

    @RequiresPermissions("purchase:sign:edit")
    @RepeatSubmit
    @PutMapping
    public R edit(@RequestBody @Validated(UpdateGroup.class) CommonVo<SysPurchaseSign, SysPurchaseSignSub> vo) {
        return R.success().data(purchaseSignService.edit(vo));
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
    public R submit(@RequestBody @Validated(SubmitGroup.class) CommonVo<SysPurchaseSign, SysPurchaseSignSub> vo) {
        return R.success().data(purchaseSignService.submit(vo));
    }

    @RequiresPermissions("purchase:sign:audit")
    @RepeatSubmit
    @PutMapping("/audit")
    public R audit(@RequestBody @Validated(AuditGroup.class) CommonVo<SysPurchaseSign, SysPurchaseSignSub> vo) {
        return R.success().data(purchaseSignService.audit(vo));
    }

}

