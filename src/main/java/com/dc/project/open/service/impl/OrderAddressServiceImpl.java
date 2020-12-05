package com.dc.project.open.service.impl;

import com.dc.project.open.entity.OrderAddress;
import com.dc.project.open.dao.OrderAddressDao;
import com.dc.project.open.service.IOrderAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 客户订单收货地址表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-11-28
 */
@Service
public class OrderAddressServiceImpl extends ServiceImpl<OrderAddressDao, OrderAddress> implements IOrderAddressService {

}
