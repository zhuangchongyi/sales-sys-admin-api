package com.dc.project.sales.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.sales.entity.SysSignback;
import com.baomidou.mybatisplus.extension.service.IService;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 销售签回主表 服务类
 *
 * @author zhuangcy
 * @since 2020-10-10
 */
public interface ISysSignbackService extends IService<SysSignback> {

    IPage<SysSignback> page(Page<SysSignback> page, SysSignback sysSignback);

    boolean edit(Map<String, Object> formMap) throws InvocationTargetException, IllegalAccessException;

    SysSignback get(Integer id);

    boolean audit(SysSignback sysSignback);
}
