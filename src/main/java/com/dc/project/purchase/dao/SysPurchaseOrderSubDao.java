package com.dc.project.purchase.dao;

import com.dc.project.purchase.entity.SysPurchaseOrderSub;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 采购订单子表 Mapper 接口
 *
 * @author zhuangcy
 * @since 2020-12-02
 */
public interface SysPurchaseOrderSubDao extends BaseMapper<SysPurchaseOrderSub> {

    List<SysPurchaseOrderSub> listOrderSubs(SysPurchaseOrderSub orderSub);
}
