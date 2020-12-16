package com.dc.project.purchase.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.constant.SalesConstant;
import com.dc.common.lang.annotation.RepeatSubmit;
import com.dc.common.valid.AuditGroup;
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
 * 采购入库 前端控制器
 *
 * @author zhuangcy
 * @since 2020-12-05
 */
@RestController
@RequestMapping("/purchase/storage")
public class SysPurchaseStorageController {

    @Autowired
    private ISysPurchaseSignService purchaseSignService;

    @RequiresPermissions("purchase:storage:list")
    @GetMapping
    public R page(Page<SysPurchaseSign> page, SysPurchaseSign sign) {
        sign.setStatus(SalesConstant.AUDIT);
        return R.success().data(purchaseSignService.page(page, sign));
    }

    @GetMapping("/{id}")
    public R get(@PathVariable Integer id) {
        return R.success().data(purchaseSignService.get(id));
    }

    @RequiresPermissions("purchase:storage:edit")
    @RepeatSubmit
    @PutMapping
    public R edit(@RequestBody @Validated(UpdateGroup.class) CommonVo<SysPurchaseSign, SysPurchaseSignSub> vo) {
        return R.success().data(purchaseSignService.edit(vo));
    }

    @RequiresPermissions("purchase:storage:audit")
    @RepeatSubmit
    @PutMapping("/audit")
    public R audit(@RequestBody @Validated(AuditGroup.class) CommonVo<SysPurchaseSign, SysPurchaseSignSub> vo) {
        return R.success().data(purchaseSignService.auditStorage(vo));
    }

}

