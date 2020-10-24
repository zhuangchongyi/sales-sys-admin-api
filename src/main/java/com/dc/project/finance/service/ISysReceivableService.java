package com.dc.project.finance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.project.finance.entity.SysReceivable;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 财务应收款主表 服务类
 *
 * @author zhuangcy
 * @since 2020-10-21
 */
public interface ISysReceivableService extends IService<SysReceivable> {

    boolean submit(Integer[] ids, String status);

    boolean audit(SysReceivable receivable);

    String saveAndUpdate(Map<String, Object> formMap) throws InvocationTargetException, IllegalAccessException;

    boolean delete(Integer id);
}
