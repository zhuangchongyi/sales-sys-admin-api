package com.dc.project.sales.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.lang.annotation.RepeatSubmit;
import com.dc.common.constant.CustomConstant;
import com.dc.common.vo.R;
import com.dc.project.sales.entity.SysShipments;
import com.dc.project.sales.service.ISysShipmentsService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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

    @RequiresPermissions(value = {"sales:shipments:list"})
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
    @RepeatSubmit
    @RequiresPermissions(value = {"sales:shipments:add", "sales:shipments:edit"}, logical = Logical.OR)
    @PostMapping
    public R saveAndUpdate(@RequestBody Map formMap) throws Exception {
        return R.success().data(shipmentsService.saveAndUpdate(formMap));
    }

    @RepeatSubmit
    @RequiresPermissions(value = {"sales:shipments:delete"})
    @DeleteMapping("/{shipmentId}")
    public R delete(@PathVariable Integer shipmentId) {
        return R.success().data(shipmentsService.delete(shipmentId));
    }

    @RepeatSubmit
    @RequiresPermissions(value = {"sales:shipments:submit", "warehouse:outbound:submit"}, logical = Logical.OR)
    @PutMapping("/submit/{status}")
    public R submit(@RequestBody Integer[] ids, @PathVariable String status) {
        return R.success().data(shipmentsService.submit(ids, status));
    }

    @RepeatSubmit
    @RequiresPermissions(value = {"sales:shipments:audit", "sales:shipments:approve"}, logical = Logical.OR)
    @PutMapping("/audit")
    public R audit(@RequestBody SysShipments shipment) {
        return R.success().data(shipmentsService.audit(shipment));
    }


    //********** 出库 ***********//
    @RequiresPermissions("warehouse:outbound:list")
    @GetMapping("/outbound")
    public R pageOutboud(Page page, SysShipments shipment) {
        shipment.setShipmentsStatus(CustomConstant.YES_STATUS);
        return R.success().data(shipmentsService.outboundPage(page, shipment));
    }

    @RepeatSubmit
    @RequiresPermissions("warehouse:outbound:edit")
    @PutMapping("/outbound")
    public R updateOutbound(@RequestBody Map formMap) throws Exception {
        return R.success().data(shipmentsService.saveAndUpdate(formMap));
    }

    @RepeatSubmit
    @RequiresPermissions(value = {"warehouse:outbound:audit"})
    @PutMapping("/outbound/audit")
    public R auditOutbound(@RequestBody SysShipments shipment) {
        return R.success().data(shipmentsService.auditOutbound(shipment));
    }

}

