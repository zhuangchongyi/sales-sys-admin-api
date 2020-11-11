package com.dc.project.sales.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.lang.annotation.RepeatSubmit;
import com.dc.common.vo.R;
import com.dc.project.sales.entity.SysOrder;
import com.dc.project.sales.service.ISysOrderService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 销售订单主表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-09-17
 */
@RestController
@RequestMapping("/sales/order")
public class SysOrderController {
    @Autowired
    private ISysOrderService orderService;

    @RequiresPermissions("sales:order:list")
    @GetMapping
    public R page(Page page, SysOrder order) {
        return R.success().data(orderService.page(page, order));
    }

    @GetMapping("/list")
    public R list(Page page, SysOrder order) {
        return R.success().data(orderService.list(page, order));
    }

    @GetMapping("/{orderId}")
    public R get(@PathVariable Integer orderId) {
        return R.success().data(orderService.get(orderId));
    }

    /**
     * 新增修改订单
     *
     * @param formMap {clientele:{},delSubIds:[],materielList:[]}
     * @return
     * @throws Exception
     */
    @RepeatSubmit
    @RequiresPermissions(value = {"sales:order:add", "sales:order:edit"}, logical = Logical.OR)
    @PostMapping
    public R saveAndUpdate(@RequestBody Map formMap) throws Exception {
        return R.success().data(orderService.saveAndUpdate(formMap));
    }

    @RepeatSubmit
    @RequiresPermissions(value = "sales:order:delete")
    @DeleteMapping("/{orderId}")
    public R delete(@PathVariable Integer orderId) {
        return R.success().data(orderService.delete(orderId));
    }

    @RepeatSubmit
    @RequiresPermissions(value = "sales:order:submit")
    @PutMapping("/submit/{status}")
    public R submit(@RequestBody Integer[] ids, @PathVariable String status) {
        return R.success().data(orderService.submit(ids, status));
    }

    @RepeatSubmit
    @RequiresPermissions(value = "sales:order:audit")
    @PutMapping("/audit")
    public R audit(@RequestBody SysOrder order) {
        return R.success().data(orderService.audit(order));
    }

    /**
     * 校验是否可以关闭订单
     *
     * @param order
     * @return
     */
    @GetMapping("/checkClose")
    public R checkCloseOrder(SysOrder order) {
        return R.success().data(orderService.checkCloseOrder(order));
    }

    @RepeatSubmit
    @RequiresPermissions(value = "sales:order:close")
    @PutMapping("/close")
    public R closeOrder(@RequestBody SysOrder order) {
        return R.success().data(orderService.closeOrder(order));
    }

    /**
     * 查询可退货订单
     *
     * @param page
     * @param order
     * @return
     */
    @GetMapping("/returns")
    public R findReturnsOrder(Page page, SysOrder order) {
        return R.success().data(orderService.findReturnsOrder(page, order));
    }

}

