package com.dc.project.sales.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.sales.entity.SysQuotation;
import org.apache.ibatis.annotations.Param;

/**
 * 销售报价主表 Mapper 接口
 *
 * @author zhuangcy
 * @since 2020-09-12
 */
public interface SysQuotationDao extends BaseMapper<SysQuotation> {

    IPage<SysQuotation> page(Page page, @Param("sq") SysQuotation quotation);

    SysQuotation get(Integer quotationId);
}
