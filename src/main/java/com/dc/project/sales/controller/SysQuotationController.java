package com.dc.project.sales.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.lang.annotation.RepeatSubmit;
import com.dc.common.vo.R;
import com.dc.project.sales.entity.SysQuotation;
import com.dc.project.sales.service.ISysQuotationService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 销售报价主表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-09-12
 */
@RestController
@RequestMapping("/sales/quotation")
public class SysQuotationController {
    @Autowired
    private ISysQuotationService quotationService;

    @RequiresPermissions(value = "sales:quotation:list")
    @GetMapping
    public R page(Page page, SysQuotation quotation) {
        return R.success().data(quotationService.page(page, quotation));
    }

    @GetMapping("/{quotationId}")
    public R get(@PathVariable Integer quotationId) {
        return R.success().data(quotationService.get(quotationId));
    }

    /**
     * 新增修改报价单
     *
     * @param formMap {clientele，materielList，delSubIds}
     * @return
     * @throws Exception
     */
    @RepeatSubmit
    @RequiresPermissions(value = {"sales:quotation:add", "sales:quotation:edit"}, logical = Logical.OR)
    @PostMapping
    public R saveAndUpdate(@RequestBody Map formMap) throws Exception {
        return R.success().data(quotationService.saveAndUpdate(formMap));
    }

    @RepeatSubmit
    @RequiresPermissions(value = "sales:quotation:delete")
    @DeleteMapping("/{quotationId}")
    public R delete(@PathVariable Integer quotationId) {
        return R.success().data(quotationService.delete(quotationId));
    }

    @RepeatSubmit
    @RequiresPermissions(value = "sales:quotation:submit")
    @PutMapping("/submit/{status}")
    public R submit(@RequestBody Integer[] ids, @PathVariable String status) {
        return R.success().data(quotationService.submit(ids, status));
    }

    @RepeatSubmit
    @RequiresPermissions(value = "sales:quotation:audit")
    @PutMapping("/audit")
    public R audit(@RequestBody SysQuotation quotation) {
        return R.success().data(quotationService.audit(quotation));
    }

    /**
     * 根据报价单生成订单
     *
     * @param formMap {clientele，materielList}
     */
    @RepeatSubmit
    @RequiresPermissions(value = "sales:quotation:order")
    @PostMapping("/order")
    public R order(@RequestBody Map<String, Object> formMap) throws InvocationTargetException, IllegalAccessException {
        return R.success().data(quotationService.saveOrder(formMap));
    }

}

