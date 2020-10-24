package com.dc.project.finance.service;

import com.dc.project.finance.entity.SysFinanceInit;
import com.baomidou.mybatisplus.extension.service.IService;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 财务初始化表 服务类
 *
 * @author zhuangcy
 * @since 2020-10-19
 */
public interface ISysFinanceInitService extends IService<SysFinanceInit> {

    boolean saveAndUpdate(Map<String, Object> formMap) throws InvocationTargetException, IllegalAccessException;

    boolean delete(Integer id);
}
