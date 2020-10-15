package com.dc.project.warehouse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.project.warehouse.entity.SysWarehouseInit;

import java.util.Map;

/**
 * 仓库初始化主表 服务类
 *
 * @author zhuangcy
 * @since 2020-09-21
 */
public interface ISysWarehouseInitService extends IService<SysWarehouseInit> {

    boolean addAndUpdate(Map formMap) throws Exception;

    boolean delete(Integer id);
}
