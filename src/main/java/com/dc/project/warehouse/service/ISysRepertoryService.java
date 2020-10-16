package com.dc.project.warehouse.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.project.warehouse.entity.SysRepertory;
import com.dc.project.warehouse.entity.SysWarehouseInitSub;

import java.util.List;

/**
 * 产品现存表 服务类
 *
 * @author zhuangcy
 * @since 2020-09-21
 */
public interface ISysRepertoryService extends IService<SysRepertory> {
    IPage<SysRepertory> page(Page page, SysRepertory sysRepertory);

    List<SysRepertory> list(SysRepertory sysRepertory);

    boolean saveAndUpdate(List<SysRepertory> list);

    SysRepertory getSysRepertory(SysRepertory repertory);

    SysRepertory getRepertory(SysWarehouseInitSub sub);
}
