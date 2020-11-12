package com.dc.project.warehouse.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.warehouse.entity.SysInventory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 盘点单主表 服务类
 *
 * @author zhuangcy
 * @since 2020-09-23
 */
public interface ISysInventoryService extends IService<SysInventory> {

    IPage<SysInventory> page(Page<SysInventory> page, SysInventory inventory);

    boolean addAndUpdate(Map formMap) throws Exception;

    boolean delete(Integer id);

    boolean submit(Integer[] ids, String status);

    boolean audit(SysInventory inventory);
}
