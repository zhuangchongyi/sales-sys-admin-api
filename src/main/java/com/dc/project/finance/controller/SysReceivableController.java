package com.dc.project.finance.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.vo.R;
import com.dc.project.finance.entity.SysReceivable;
import com.dc.project.finance.service.ISysReceivableService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 财务应收款主表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-10-21
 */
@RestController
@RequestMapping("/finance/receivable")
public class SysReceivableController {
    @Autowired
    private ISysReceivableService receivableService;

    @GetMapping
    public R page(Page page, SysReceivable receivable) {
        QueryWrapper<SysReceivable> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(receivable.getReceivableNum()), "receivable_num", receivable.getReceivableNum())
                .or().like(StringUtils.isNotEmpty(receivable.getClienteleNum()), "clientele_num", receivable.getClienteleNum())
                .or().like(StringUtils.isNotEmpty(receivable.getClienteleName()), "clientele_name", receivable.getClienteleName());
        queryWrapper.orderByDesc("create_time");
        return R.success().data(receivableService.page(page, queryWrapper));
    }

    @GetMapping("/{id}")
    public R get(@PathVariable Integer id) {
        QueryWrapper<SysReceivable> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(SysReceivable.class,
                info -> !info.getColumn().equals("create_time") && !info.getColumn().equals("update_time") && !info.getColumn().equals("audit_time"));
        queryWrapper.eq("receivable_id", id);
        return R.success().data(receivableService.getOne(queryWrapper));
    }

    @PostMapping
    public R add(@RequestBody Map<String, Object> formMap) throws InvocationTargetException, IllegalAccessException {
        return R.success().data(receivableService.saveAndUpdate(formMap));
    }

    @PutMapping
    public R edit(@RequestBody Map<String, Object> formMap) throws InvocationTargetException, IllegalAccessException {
        return R.success().data(receivableService.saveAndUpdate(formMap));
    }

    @PutMapping("/submit/{status}")
    public R submit(@RequestBody Integer[] ids, @PathVariable String status) {
        return R.success().data(receivableService.submit(ids, status));
    }

    @PutMapping("/audit")
    public R audit(@RequestBody SysReceivable receivable) {
        return R.success().data(receivableService.audit(receivable));
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        return R.success().data(receivableService.delete(id));
    }
}

