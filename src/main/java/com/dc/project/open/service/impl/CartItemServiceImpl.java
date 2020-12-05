package com.dc.project.open.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.exception.ServiceException;
import com.dc.project.open.dao.CartItemDao;
import com.dc.project.open.entity.CartItem;
import com.dc.project.open.service.ICartItemService;
import com.dc.project.sales.service.ISysOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 购物车表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-11-27
 */
@Service
public class CartItemServiceImpl extends ServiceImpl<CartItemDao, CartItem> implements ICartItemService {
    @Autowired
    private ISysOrderService orderService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertOrUpdate(CartItem cartItem) {
        LambdaQueryWrapper<CartItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CartItem::getItemId, cartItem.getItemId())
                .eq(CartItem::getModelName, cartItem.getModelName());
        CartItem one = this.getOne(wrapper, false);
        if (null == one) {
            if (!this.save(cartItem)) {
                throw new ServiceException("添加产品失败");
            }
        } else {
            one.setNumber(one.getNumber() + cartItem.getNumber());
            if (!this.updateById(one)) {
                throw new ServiceException("修改产品失败");
            }
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addOrder(List<CartItem> cartItems) {
        if (cartItems != null && !cartItems.isEmpty()) {
            List<Long> ids = cartItems.stream().map(CartItem::getCartItemId).collect(Collectors.toList());
            if (orderService.addOrder(cartItems) && this.removeByIds(ids)) {
                return true;
            }
        }
        throw new ServiceException("订单保存失败");
    }
}
