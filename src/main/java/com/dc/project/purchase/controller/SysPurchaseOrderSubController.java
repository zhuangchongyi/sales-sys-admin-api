package com.dc.project.purchase.controller;


import com.dc.common.vo.R;
import com.dc.project.purchase.entity.SysPurchaseOrderSub;
import com.dc.project.purchase.service.ISysPurchaseOrderSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * 采购订单子表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-12-02
 */
@RestController
@RequestMapping("/purchase/orderSub")
public class SysPurchaseOrderSubController {
    @Autowired
    private ISysPurchaseOrderSubService orderSubService;

    @GetMapping("/list")
    public R list(SysPurchaseOrderSub orderSub) {
        return R.success().data(orderSubService.list(orderSub));
    }

}

