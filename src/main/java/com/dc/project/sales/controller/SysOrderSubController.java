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
        queryWrapper.eq(null != orderSub.getOrderId(), "order_id", orderSub.getOrderId());
        queryWrapper.orderByDesc("create_time");
        return R.success().data(orderSubService.list(queryWrapper));
    }

    @GetMapping
    public R get(SysOrderSub orderSub) {
        QueryWrapper<SysOrderSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("sub_id,materiel_id, materiel_num, materiel_name, specification, model_name, need_torque, out_torque, units_id, units_name, price, number, total_price, demand, has_shipment_num, has_outbound_num, has_signback_num, shipment_num, outbound_num, singback_num, not_shipment_num, not_signback_num");
        queryWrapper.eq(null != orderSub.getOrderId(), "order_id", orderSub.getOrderId());
        queryWrapper.orderByDesc("create_time");
        return R.success().data(orderSubService.list(queryWrapper));
    }

    @DeleteMapping("/{subId}")
    public R delete(@PathVariable Long subId) {
        return R.success().data(orderSubService.delete(subId));
    }


}

