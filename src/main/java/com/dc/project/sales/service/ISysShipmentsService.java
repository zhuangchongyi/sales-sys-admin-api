package com.dc.project.sales.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.project.sales.entity.SysShipments;

import java.util.Map;

/**
 * 销售发货出库主表 服务类
 *
 * @author zhuangcy
 * @since 2020-09-21
 */
public interface ISysShipmentsService extends IService<SysShipments> {

    IPage<SysShipments> page(Page page, SysShipments shipments);

    IPage<SysShipments> outboundPage(Page page, SysShipments shipments);

    SysShipments get(Integer shipmentId);

    boolean saveAndUpdate(Map formMap) throws Exception;

    boolean delete(Integer shipmentId);

    boolean submit(Integer[] ids, String status);

    boolean audit(SysShipments shipment);

    boolean auditOutbound(SysShipments shipments);
}
