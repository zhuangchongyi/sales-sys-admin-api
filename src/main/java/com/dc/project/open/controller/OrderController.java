package com.dc.project.open.controller;

import com.dc.common.vo.R;
import com.dc.project.open.service.IOrderService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author zhuangcy
 * @date 2020/11/16 9:36
 * @description 订单请求控制层
 */
@Api(tags = "订单请求接口")
@RestController
@RequestMapping("/open")
@Validated
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @ApiOperation(value = "客户订单列表")
    @RequiresPermissions("open:order:list")
    @GetMapping("/order/{id}")
    public R order(
            @ApiParam(name = "id", value = "客户id", required = true)
            @NotNull
            @PathVariable Integer id) {
        return R.success().data(orderService.listOrder(id));
    }

    @ApiOperation(value = "客户订单明细")
    @RequiresPermissions("open:order:list")
    @GetMapping("/order/detail/{id}")
    public R orderDetail(
            @ApiParam(name = "id", value = "订单id", required = true)
            @NotNull
            @PathVariable Integer id) {
        return R.success().data(orderService.listOrderDetail(id));
    }

    @ApiOperation(value = "客户发货单列表")
    @RequiresPermissions("open:shipment:list")
    @GetMapping("/shipment/{id}")
    public R shipment(
            @ApiParam(name = "id", value = "客户id", required = true)
            @NotNull
            @PathVariable Integer id) {
        return R.success().data(orderService.listShipment(id));
    }

    @ApiOperation(value = "客户发货单明细")
    @RequiresPermissions("open:order:list")
    @GetMapping("/shipment/detail/{id}")
    public R shipmentDetail(
            @ApiParam(name = "id", value = "发货单id", required = true)
            @NotNull
            @PathVariable Integer id) {
        return R.success().data(orderService.listShipmentDetail(id));
    }

    @ApiOperation(value = "客户签收单列表")
    @RequiresPermissions("open:sign:list")
    @GetMapping("/sign/{id}")
    public R sign(
            @ApiParam(name = "id", value = "客户id", required = true)
            @NotNull
            @PathVariable Integer id) {
        return R.success().data(orderService.listSign(id));
    }

    @ApiOperation(value = "客户签收单明细")
    @RequiresPermissions("open:order:list")
    @GetMapping("/sign/detail/{id}")
    public R signDetail(
            @ApiParam(name = "id", value = "签收单id", required = true)
            @NotNull
            @PathVariable Integer id) {
        return R.success().data(orderService.listSignDetail(id));
    }

}
