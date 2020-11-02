package com.dc.project.sales.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.project.sales.entity.SysOrder;

import java.util.Map;

/**
 * 销售订单主表 服务类
 *
 * @author zhuangcy
 * @since 2020-09-17
 */
public interface ISysOrderService extends IService<SysOrder> {


    IPage<SysOrder> page(Page page, SysOrder order);

    Map<String, Object> saveAndUpdate(Map formMap) throws Exception;

    SysOrder get(Integer orderId);

    boolean delete(Integer orderId);

    boolean submit(Integer[] ids, String status);

    boolean audit(SysOrder order);

    IPage<SysOrder> list(Page page, SysOrder order);

    boolean closeOrder(SysOrder order);

    IPage<SysOrder> findReturnsOrder(Page page, SysOrder order);

    String checkCloseOrder(SysOrder order);
}
