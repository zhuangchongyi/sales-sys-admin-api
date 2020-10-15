package com.dc.project.sales.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dc.common.vo.R;
import com.dc.project.sales.entity.SysShipmentsSub;
import com.dc.project.sales.service.ISysShipmentsSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 销售发货出库子表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-09-21
 */
@RestController
@RequestMapping("/sales/shipmentsSub")
public class SysShipmentsSubController {

    @Autowired
    private ISysShipmentsSubService shipmentsSubService;

    @GetMapping("/list")
    public R list(SysShipmentsSub shipments) {
        QueryWrapper<SysShipmentsSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(null != shipments.getShipmentsId(), "shipments_id", shipments.getShipmentsId());
        return R.success().data(shipmentsSubService.list(queryWrapper));
    }

}

