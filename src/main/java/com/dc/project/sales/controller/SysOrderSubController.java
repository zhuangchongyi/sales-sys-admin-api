package com.dc.project.sales.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dc.common.vo.R;
import com.dc.project.sales.entity.SysOrderSub;
import com.dc.project.sales.service.ISysOrderSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 销售订单子表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-09-17
 */
@RestController
@RequestMapping("/sales/orderSub")
public class SysOrderSubController {
    @Autowired
    private ISysOrderSubService orderSubService;


    @GetMapping("/list")
    public R list(SysOrderSub orderSub) {
        QueryWrapper<SysOrderSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(SysOrderSub.class, info -> !info.getColumn().equals("create_time") && !info.getColumn().equals("update_time"));
        queryWrapper.eq(null != orderSub.getOrderId(), "order_id", orderSub.getOrderId());
        queryWrapper.orderByDesc("sub_id");
        return R.success().data(orderSubService.list(queryWrapper));
    }

    @GetMapping("/sign")
    public R findSignOrderSub(SysOrderSub orderSub) {
        QueryWrapper<SysOrderSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(SysOrderSub.class, info -> !info.getColumn().equals("create_time") && !info.getColumn().equals("update_time"));
        queryWrapper.eq(null != orderSub.getOrderId(), "order_id", orderSub.getOrderId());
        queryWrapper.gt("has_signback_num",0);
        queryWrapper.orderByDesc("sub_id");
        return R.success().data(orderSubService.list(queryWrapper));
    }

    @GetMapping
    public R get(SysOrderSub orderSub) {
        return R.success().data(orderSubService.getOrderSub(orderSub));
    }

    @DeleteMapping("/{subId}")
    public R delete(@PathVariable Long subId) {
        return R.success().data(orderSubService.delete(subId));
    }


}

