package com.dc.project.open.service;

import com.dc.project.open.entity.CartItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 购物车表 服务类
 *
 * @author zhuangcy
 * @since 2020-11-27
 */
public interface ICartItemService extends IService<CartItem> {

    /**
     * 新增或修改购物车产品
     *
     * @param cartItem
     * @return
     */
    boolean insertOrUpdate(CartItem cartItem);

    /**
     * 购物车产品添加订单
     *
     * @param cartItems
     * @return
     */
    boolean addOrder(List<CartItem> cartItems);
}
