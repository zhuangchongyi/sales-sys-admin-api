package com.dc.project.purchase.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.lang.annotation.RepeatSubmit;
import com.dc.common.valid.AuditGroup;
import com.dc.common.valid.InsertGroup;
import com.dc.common.valid.SubmitGroup;
import com.dc.common.valid.UpdateGroup;
import com.dc.common.vo.R;
import com.dc.project.purchase.entity.SysPurchaseOrder;
import com.dc.project.purchase.entity.SysPurchaseOrderSub;
import com.dc.project.purchase.service.ISysPurchaseOrderService;
import com.dc.common.vo.CommonVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 采购订单主表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-12-02
 */
@RestController
@RequestMapping("/purchase/order")
public class SysPurchaseOrderController {
    @Autowired
    private ISysPurchaseOrderService purchaseOrderService;

    @RequiresPermissions("purchase:order:list")
    @GetMapping
    public R page(Page<SysPurchaseOrder> page, SysPurchaseOrder order) {
        return R.success().data(purchaseOrderService.page(page, order));
    }

    @GetMapping("/list")
    public R list(Page<SysPurchaseOrder> page, SysPurchaseOrder order) {
        return R.success().data(purchaseOrderService.list(page, order));
    }

    @GetMapping("/{id}")
    public R get(@PathVariable Integer id) {
        return R.success().data(purchaseOrderService.get(id));
    }

    @RequiresPermissions("purchase:order:add")
    @RepeatSubmit
    @PostMapping
    public R add(@RequestBody @Validated(InsertGroup.class) CommonVo<SysPurchaseOrder, SysPurchaseOrderSub> vo) {
        return R.success().data(purchaseOrderService.add(vo));
    }

    @RequiresPermissions("purchase:order:edit")
    @RepeatSubmit
    @PutMapping
    public R edit(@RequestBody @Validated(UpdateGroup.class) CommonVo<SysPurchaseOrder, SysPurchaseOrderSub> vo) {
        return R.success().data(purchaseOrderService.edit(vo));
    }

    @RequiresPermissions("purchase:order:delete")
    @RepeatSubmit
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        return R.success().data(purchaseOrderService.delete(id));
    }

    @RequiresPermissions("purchase:order:submit")
    @RepeatSubmit
    @PutMapping("/submit")
    public R submit(@RequestBody @Validated(SubmitGroup.class) CommonVo<SysPurchaseOrder, SysPurchaseOrderSub> vo) {
        return R.success().data(purchaseOrderService.submit(vo));
    }

    @RequiresPermissions("purchase:order:audit")
    @RepeatSubmit
    @PutMapping("/audit")
    public R audit(@RequestBody @Validated(AuditGroup.class) CommonVo<SysPurchaseOrder, SysPurchaseOrderSub> vo) {
        return R.success().data(purchaseOrderService.audit(vo));
    }
}

