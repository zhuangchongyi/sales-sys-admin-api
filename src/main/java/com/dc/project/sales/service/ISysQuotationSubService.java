package com.dc.project.sales.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.common.vo.QuotationSubVo;
import com.dc.project.sales.entity.SysQuotationSub;

/**
 * 销售报价子表 服务类
 *
 * @author zhuangcy
 * @since 2020-09-12
 */
public interface ISysQuotationSubService extends IService<SysQuotationSub> {

    IPage<QuotationSubVo> page(Page page, QuotationSubVo quotationSubVo);
}
