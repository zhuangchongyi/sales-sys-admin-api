package com.dc.project.basis.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.project.basis.entity.SysWarehouse;

/**
 * 仓库表 服务类
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
public interface ISysWarehouseService extends IService<SysWarehouse> {

    IPage<SysWarehouse> list(Page page, SysWarehouse warehouse);

    SysWarehouse getByWarehouseId(Integer warehouseId);

    IPage initPage(Page page, SysWarehouse warehouse);
}
