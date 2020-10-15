package com.dc.project.sales.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.sales.entity.SysShipments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 销售发货出库主表 Mapper 接口
 *
 * @author zhuangcy
 * @since 2020-09-21
 */
public interface SysShipmentsDao extends BaseMapper<SysShipments> {

    IPage<SysShipments> page(Page page, @Param("ss") SysShipments shipments);

    SysShipments get(Integer shipmentsId);
}
