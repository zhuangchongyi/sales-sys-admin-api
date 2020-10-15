package com.dc.project.sales.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.vo.QuotationSubVo;
import com.dc.project.sales.entity.SysQuotationSub;
import com.dc.project.sales.dao.SysQuotationSubDao;
import com.dc.project.sales.service.ISysQuotationSubService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 销售报价子表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-09-12
 */
@Service
public class SysQuotationSubServiceImpl extends ServiceImpl<SysQuotationSubDao, SysQuotationSub> implements ISysQuotationSubService {

    @Override
    public IPage<QuotationSubVo> page(Page page, QuotationSubVo quotationSubVo) {
        return baseMapper.page(page, quotationSubVo);
    }
}
