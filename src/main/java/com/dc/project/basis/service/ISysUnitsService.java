package com.dc.project.basis.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.basis.entity.SysUnits;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 基本单位表 服务类
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
public interface ISysUnitsService extends IService<SysUnits> {

    IPage list(Page page, SysUnits units);
}
