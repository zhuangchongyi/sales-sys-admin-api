package com.dc.project.sales.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.project.sales.entity.SysQuotation;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 销售报价主表 服务类
 *
 * @author zhuangcy
 * @since 2020-09-12
 */
public interface ISysQuotationService extends IService<SysQuotation> {

    IPage<SysQuotation> page(Page page, SysQuotation quotation);

    Map<String, Object> saveAndUpdate(Map formMap) throws Exception;

    SysQuotation get(Integer quotationId);

    boolean delete(Integer quotationId);

    boolean submit(Integer[] ids, String status);

    boolean audit(SysQuotation quotation);

    String saveOrder(Map<String, Object> formMap) throws InvocationTargetException, IllegalAccessException;
}
