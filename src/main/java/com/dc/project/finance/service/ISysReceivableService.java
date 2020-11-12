package com.dc.project.finance.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.common.lang.annotation.DataScope;
import com.dc.project.finance.entity.SysReceivable;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 财务应收款主表 服务类
 *
 * @author zhuangcy
 * @since 2020-10-21
 */
public interface ISysReceivableService extends IService<SysReceivable> {

    @DataScope(userColumn = "create_id")
    IPage<SysReceivable> page(Page<SysReceivable> page, SysReceivable receivable);

    boolean submit(Integer[] ids, String status);

    boolean audit(SysReceivable receivable);

    String saveAndUpdate(Map<String, Object> formMap) throws InvocationTargetException, IllegalAccessException;

    boolean delete(Integer id);

    BigDecimal findReceivePriceByClienteleId(Integer id);

    List<SysReceivable> getClienteleReceivable(SysReceivable receivable);

    List<SysReceivable> getClienteleReceivableList(SysReceivable receivable);
}
