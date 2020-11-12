package com.dc.project.warehouse.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.warehouse.entity.SysAdjustment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 调整单主表 服务类
 *
 * @author zhuangcy
 * @since 2020-09-23
 */
public interface ISysAdjustmentService extends IService<SysAdjustment> {

    IPage<SysAdjustment> page(Page<SysAdjustment> page, SysAdjustment adjustment);

    boolean addAndUpdate(Map formMap) throws Exception;

    boolean delete(Integer id);

    boolean audit(SysAdjustment adjustment);

    boolean submit(Integer[] ids, String status);

}
