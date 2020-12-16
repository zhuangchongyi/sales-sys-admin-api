package com.dc.project.open.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dc.common.lang.annotation.RepeatSubmit;
import com.dc.common.vo.OpenUser;
import com.dc.common.vo.R;
import com.dc.project.open.entity.CartItem;
import com.dc.project.open.service.ICartItemService;
import com.dc.project.open.vo.CartOrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 购物车表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-11-27
 */
@Api(tags = "购物车请求接口")
@RestController
@RequestMapping("/open/cart")
@Validated
public class CartItemController {
    @Autowired
    private ICartItemService cartItemService;

    @ApiOperation(value = "产品添加购物车")
    @RepeatSubmit
    @PostMapping
    public R add(@RequestBody CartItem cartItem) {
        return R.success().data(cartItemService.insertOrUpdate(cartItem));
    }

    @ApiOperation(value = "购物车产品列表")
    @GetMapping
    public R list() {
        OpenUser clientele = (OpenUser) SecurityUtils.getSubject().getPrincipal();
        LambdaQueryWrapper<CartItem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CartItem::getClienteleId, clientele.getClientele().getClienteleId());
        queryWrapper.orderByDesc(CartItem::getCartItemId);
        return R.success().data(cartItemService.list(queryWrapper));
    }


    @ApiOperation(value = "购物车产品生成订单")
    @RepeatSubmit
    @PostMapping("/order")
    public R addOrder(@RequestBody @Validated CartOrderVo cartOrder) {
        return R.success().data(cartItemService.addOrder(cartOrder));
    }


}

