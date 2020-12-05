package com.dc.project.purchase.service;

import com.dc.project.purchase.entity.SysPurchaseOrderSub;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 采购订单子表 服务类
 *
 * @author zhuangcy
 * @since 2020-12-02
 */
public interface ISysPurchaseOrderSubService extends IService<SysPurchaseOrderSub> {

    List<SysPurchaseOrderSub> list(SysPurchaseOrderSub orderSub);

}
