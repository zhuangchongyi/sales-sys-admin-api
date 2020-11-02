package com.dc.project.finance.service;

import com.dc.project.finance.entity.SysWriteoff;
import com.baomidou.mybatisplus.extension.service.IService;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 应收核销表 服务类
 *
 * @author zhuangcy
 * @since 2020-10-26
 */
public interface ISysWriteoffService extends IService<SysWriteoff> {

    boolean insertAndUpdate(Map<String, Object> formMap) throws InvocationTargetException, IllegalAccessException;

    boolean delete(Integer id);

    boolean audit(SysWriteoff writeoff);
}
