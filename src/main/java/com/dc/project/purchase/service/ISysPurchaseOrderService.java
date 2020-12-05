package com.dc.project.purchase.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.purchase.entity.SysPurchaseOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.project.purchase.vo.PurchaseVo;

/**
 * 采购订单主表 服务类
 *
 * @author zhuangcy
 * @since 2020-12-02
 */
public interface ISysPurchaseOrderService extends IService<SysPurchaseOrder> {

    IPage<SysPurchaseOrder> page(Page<SysPurchaseOrder> page, SysPurchaseOrder order);

    IPage<SysPurchaseOrder>  list(Page<SysPurchaseOrder> page, SysPurchaseOrder order);

    SysPurchaseOrder get(Integer id);

    String add(PurchaseVo orderVo);

    boolean edit(PurchaseVo orderVo);

    boolean delete(Integer id);

    boolean submit(PurchaseVo orderVo);

    boolean audit(PurchaseVo orderVo);
}
