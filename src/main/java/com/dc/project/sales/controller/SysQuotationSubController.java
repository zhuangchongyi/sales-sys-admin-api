package com.dc.project.sales.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.vo.QuotationSubVo;
import com.dc.common.vo.R;
import com.dc.project.sales.entity.SysQuotationSub;
import com.dc.project.sales.service.ISysQuotationSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 销售报价子表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-09-12
 */
@RestController
@RequestMapping("/sales/quotationSub")
public class SysQuotationSubController {

    @Autowired
    private ISysQuotationSubService quotationSubService;

    @GetMapping("/list")
    public R list(SysQuotationSub quotationSub) {
        QueryWrapper<SysQuotationSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(null != quotationSub.getQuotationId(), "quotation_id", quotationSub.getQuotationId());
        return R.success().data(quotationSubService.list(queryWrapper));
    }


    @GetMapping("/materiel")
    public R materiel(Page page, QuotationSubVo quotation) {
        return R.success().data(quotationSubService.page(page, quotation));
    }

}

