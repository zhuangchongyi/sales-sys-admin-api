package com.dc.project.sales.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.open.vo.OrderVo;
import com.dc.project.sales.entity.SysOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 销售订单主表 Mapper 接口
 *
 * @author zhuangcy
 * @since 2020-09-17
 */
public interface SysOrderDao extends BaseMapper<SysOrder> {

    IPage<SysOrder> page(Page page, @Param("so") SysOrder order);

    SysOrder get(Integer orderId);

    IPage<SysOrder> list(Page page, @Param("so") SysOrder order);

    IPage<SysOrder> findReturnsOrder(Page page, @Param("so") SysOrder order);

    List<OrderVo> listOrder(Integer clienteleId);

}
