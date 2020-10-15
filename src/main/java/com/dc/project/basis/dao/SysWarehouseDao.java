package com.dc.project.basis.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.basis.entity.SysWarehouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 仓库表 Mapper 接口
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
public interface SysWarehouseDao extends BaseMapper<SysWarehouse> {

    IPage<SysWarehouse> list(Page page, @Param("ware") SysWarehouse warehouse);

    SysWarehouse getByWarehouseId(Integer warehouseId);

    IPage initPage(Page page, @Param("ware") SysWarehouse warehouse);
}
