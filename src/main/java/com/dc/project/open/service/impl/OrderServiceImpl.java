package com.dc.project.open.service.impl;

import com.dc.project.open.service.IOrderService;
import com.dc.project.open.vo.OrderVo;
import com.dc.project.sales.service.ISysOrderService;
import com.dc.project.sales.service.ISysShipmentsService;
import com.dc.project.sales.service.ISysSignbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private ISysOrderService orderService;
    @Autowired
    private ISysShipmentsService shipmentsService;
    @Autowired
    private ISysSignbackService signbackService;

    @Override
    public List<OrderVo> listOrder(Integer clienteleId) {
        return orderService.listOrder(clienteleId);
    }

    @Override
    public boolean add(OrderVo orderVo) {
        return false;
    }

    @Override
    public List<OrderVo> listShipment(Integer clienteleId) {
        return shipmentsService.listShipment(clienteleId);
    }

    @Override
    public List<OrderVo> listSign(Integer clienteleId) {
        return signbackService.listSign(clienteleId);
    }

}
