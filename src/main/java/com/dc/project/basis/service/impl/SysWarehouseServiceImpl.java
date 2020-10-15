package com.dc.project.basis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.basis.entity.SysWarehouse;
import com.dc.project.basis.dao.SysWarehouseDao;
import com.dc.project.basis.service.ISysWarehouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 仓库表 服务实现类
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
@Service
public class SysWarehouseServiceImpl extends ServiceImpl<SysWarehouseDao, SysWarehouse> implements ISysWarehouseService {

    @Override
    public IPage<SysWarehouse> list(Page page, SysWarehouse warehouse) {
        return baseMapper.list(page, warehouse);
    }

    @Override
    public SysWarehouse getByWarehouseId(Integer warehouseId) {
        return baseMapper.getByWarehouseId(warehouseId);
    }

    @Override
    public IPage initPage(Page page, SysWarehouse warehouse) {
        return baseMapper.initPage(page, warehouse);
    }
}
