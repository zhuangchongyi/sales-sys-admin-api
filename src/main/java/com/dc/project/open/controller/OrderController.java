package com.dc.project.open.controller;

import com.dc.common.vo.R;
import com.dc.project.open.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @GetMapping("/order/{id}")
    public R order(
            @ApiParam(name = "id", value = "客户id", required = true)
            @NotNull(message = "客户id不能为空")
            @PathVariable Integer id) {
        return R.success().data(orderService.listOrder(id));
    }

    @ApiOperation(value = "客户发货单列表")
    @GetMapping("/shipment/{id}")
    public R shipment(
            @ApiParam(name = "id", value = "客户id", required = true)
            @NotNull(message = "客户id不能为空")
            @PathVariable Integer id) {
        return R.success().data(orderService.listShipment(id));
    }

    @ApiOperation(value = "客户签收单列表")
    @GetMapping("/sign/{id}")
    public R sign(
            @ApiParam(name = "id", value = "客户id", required = true)
            @NotNull(message = "客户id不能为空")
            @PathVariable Integer id) {
        return R.success().data(orderService.listSign(id));
    }

}
