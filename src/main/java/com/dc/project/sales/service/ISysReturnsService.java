package com.dc.project.sales.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.sales.entity.SysReturns;
import com.baomidou.mybatisplus.extension.service.IService;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 销售退货主表 服务类
 *
 * @author zhuangcy
 * @since 2020-10-11
 */
public interface ISysReturnsService extends IService<SysReturns> {

    IPage<SysReturns> page(Page<SysReturns> page, SysReturns sysReturns);

    SysReturns get(Integer id);

    boolean delete(Integer id);

    String saveAndUpdate(Map<String, Object> formMap) throws InvocationTargetException, IllegalAccessException;

    boolean submit(Integer[] ids, String status);

    boolean audit(SysReturns sysReturns);

    boolean auditStorage(SysReturns sysReturns);

    boolean submitStorage(Integer[] ids, String status);
}
