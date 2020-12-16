package com.dc.project.purchase.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.project.purchase.entity.SysPurchaseOrder;
import com.dc.project.purchase.entity.SysPurchaseOrderSub;
import com.dc.common.vo.CommonVo;

/**
 * 采购订单主表 服务类
 *
 * @author zhuangcy
 * @since 2020-12-02
 */
public interface ISysPurchaseOrderService extends IService<SysPurchaseOrder> {

    IPage<SysPurchaseOrder> page(Page<SysPurchaseOrder> page, SysPurchaseOrder order);

    IPage<SysPurchaseOrder> list(Page<SysPurchaseOrder> page, SysPurchaseOrder order);

    SysPurchaseOrder get(Integer id);

    String add(CommonVo<SysPurchaseOrder, SysPurchaseOrderSub> vo);

    boolean edit(CommonVo<SysPurchaseOrder, SysPurchaseOrderSub> vo);

    boolean delete(Integer id);

    boolean submit(CommonVo<SysPurchaseOrder, SysPurchaseOrderSub> vo);

    boolean audit(CommonVo<SysPurchaseOrder, SysPurchaseOrderSub> vo);
}
