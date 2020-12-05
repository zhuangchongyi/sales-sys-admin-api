package com.dc.project.purchase.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.purchase.entity.SysPurchaseOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 采购订单主表 Mapper 接口
 *
 * @author zhuangcy
 * @since 2020-12-02
 */
public interface SysPurchaseOrderDao extends BaseMapper<SysPurchaseOrder> {

    IPage<SysPurchaseOrder> list(Page<SysPurchaseOrder> page, @Param("spo") SysPurchaseOrder order);
}
