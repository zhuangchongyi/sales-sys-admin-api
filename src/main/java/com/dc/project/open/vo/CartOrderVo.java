package com.dc.project.open.vo;

import com.dc.project.open.entity.CartItem;
import com.dc.project.open.entity.OrderAddress;

import java.util.List;

/**
 * @author zhuangcy
 * @date 2020/12/1
 * @description 购物车订单
 */
public class CartOrderVo {
    private List<CartItem> items;
    private OrderAddress address;

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public OrderAddress getAddress() {
        return address;
    }

    public void setAddress(OrderAddress address) {
        this.address = address;
    }
}
