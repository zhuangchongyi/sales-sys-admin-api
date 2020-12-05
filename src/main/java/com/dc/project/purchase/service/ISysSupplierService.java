package com.dc.project.purchase.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.purchase.entity.SysSupplier;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 供应商表 服务类
 *
 * @author zhuangcy
 * @since 2020-11-26
 */
public interface ISysSupplierService extends IService<SysSupplier> {

    /**
     * 分页查询
     *
     * @param page
     * @param supplier
     * @return
     */
    IPage<SysSupplier> page(Page<SysSupplier> page, SysSupplier supplier);

    SysSupplier get(Integer id);

    boolean add(SysSupplier supplier);

    boolean edit(SysSupplier supplier);

    boolean delete(Integer id);
}
