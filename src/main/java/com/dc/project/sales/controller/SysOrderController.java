package com.dc.project.sales.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.vo.R;
import com.dc.project.sales.entity.SysOrder;
import com.dc.project.sales.service.ISysOrderService;
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
    @PostMapping
    public R saveAndUpdate(@RequestBody Map formMap) throws Exception {
        return R.success().data(orderService.saveAndUpdate(formMap));
    }

    @DeleteMapping("/{orderId}")
    public R delete(@PathVariable Integer orderId) {
        return R.success().data(orderService.delete(orderId));
    }


    @PutMapping("/submit/{status}")
    public R submit(@RequestBody Integer[] ids, @PathVariable String status) {
        return R.success().data(orderService.submit(ids, status));
    }

    @PutMapping("/audit")
    public R audit(@RequestBody SysOrder order) {
        return R.success().data(orderService.audit(order));
    }

}

