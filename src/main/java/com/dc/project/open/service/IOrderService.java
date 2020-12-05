package com.dc.project.open.service;

import com.dc.project.open.vo.OrderVo;

import java.util.List;

public interface IOrderService {
    List<OrderVo> listOrder(Integer clienteleId);

    boolean add(OrderVo orderVo);

    List<OrderVo> listShipment(Integer clienteleId);

    List<OrderVo> listSign(Integer clienteleId);

}
