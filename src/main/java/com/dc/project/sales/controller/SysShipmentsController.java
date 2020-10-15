package com.dc.project.sales.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.vo.R;
import com.dc.project.sales.entity.SysShipments;
import com.dc.project.sales.service.ISysShipmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 销售发货出库主表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-09-21
 */
@RestController
@RequestMapping("/sales/shipments")
public class SysShipmentsController {

    @Autowired
    private ISysShipmentsService shipmentsService;

    @GetMapping
    public R page(Page page, SysShipments shipment) {
        return R.success().data(shipmentsService.page(page, shipment));
    }

    @GetMapping("/{shipmentId}")
    public R get(@PathVariable Integer shipmentId) {
        return R.success().data(shipmentsService.get(shipmentId));
    }

    /**
     * 新增修改发货单
     *
     * @param formMap {clientele:{},materielList:[]}
     * @return
     * @throws Exception
     */
    @PostMapping
    public R saveAndUpdate(@RequestBody Map formMap) throws Exception {
        return R.success().data(shipmentsService.saveAndUpdate(formMap));
    }

    @DeleteMapping("/{shipmentId}")
    public R delete(@PathVariable Integer shipmentId) {
        return R.success().data(shipmentsService.delete(shipmentId));
    }


    @PutMapping("/submit/{status}")
    public R submit(@RequestBody Integer[] ids, @PathVariable String status) {
        return R.success().data(shipmentsService.submit(ids, status));
    }

    @PutMapping("/audit")
    public R audit(@RequestBody SysShipments shipment) {
        return R.success().data(shipmentsService.audit(shipment));
    }

}

