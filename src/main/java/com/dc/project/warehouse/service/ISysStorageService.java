package com.dc.project.warehouse.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.warehouse.entity.SysStorage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 产品入库主表 服务类
 *
 * @author zhuangcy
 * @since 2020-09-22
 */
public interface ISysStorageService extends IService<SysStorage> {
    IPage<SysStorage> page(Page<SysStorage> page, SysStorage sysStorage);

    boolean addAndUpdate(Map formMap) throws Exception;

    boolean delete(Integer id);

    boolean submit(Integer[] ids, String status);

    boolean audit(SysStorage storage);
}
