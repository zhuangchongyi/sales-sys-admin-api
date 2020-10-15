package com.dc.project.warehouse.service;

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

    boolean addAndUpdate(Map formMap) throws Exception;

    boolean delete(Integer id);

    boolean submit(Integer[] ids, String status);

    boolean audit(SysInventory inventory);
}
