package com.dc.project.sales.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.vo.QuotationSubVo;
import com.dc.project.sales.entity.SysQuotationSub;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 销售报价子表 Mapper 接口
 *
 * @author zhuangcy
 * @since 2020-09-12
 */
public interface SysQuotationSubDao extends BaseMapper<SysQuotationSub> {

    IPage<QuotationSubVo> page(Page page, @Param("sqs") QuotationSubVo quotationSubVo);
}
