package com.dc.project.sales.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.vo.R;
import com.dc.project.sales.entity.SysReturns;
import com.dc.project.sales.service.ISysReturnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 销售退货主表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-10-11
 */
@RestController
@RequestMapping("/sales/returns")
public class SysReturnsController {
    @Autowired
    private ISysReturnsService returnsService;

    @GetMapping
    public R page(Page page, SysReturns sysReturns) {
        return R.success().data(returnsService.page(page, sysReturns));
    }

    @GetMapping("/{id}")
    public R get(@PathVariable Integer id) {
        return R.success().data(returnsService.get(id));
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        return R.success().data(returnsService.delete(id));
    }

    @PostMapping
    public R add(@RequestBody Map<String, Object> formMap) throws InvocationTargetException, IllegalAccessException {
        return R.success().data(returnsService.saveAndUpdate(formMap));
    }

    @PutMapping
    public R edit(@RequestBody Map<String, Object> formMap) throws InvocationTargetException, IllegalAccessException {
        return R.success().data(returnsService.saveAndUpdate(formMap));
    }

    @PutMapping("/audit")
    public R audit(@RequestBody SysReturns sysReturns) {
        return R.success().data(returnsService.audit(sysReturns));
    }

    @PutMapping("/submit/{status}")
    public R submit(@RequestBody Integer[] ids, @PathVariable String status) {
        return R.success().data(returnsService.submit(ids, status));
    }

    @PutMapping("/auditStorage")
    public R auditStorage(@RequestBody SysReturns sysReturns) {
        return R.success().data(returnsService.auditStorage(sysReturns));
    }

    @PutMapping("/submitStorage/{status}")
    public R submitStorage(@RequestBody Integer[] ids, @PathVariable String status) {
        return R.success().data(returnsService.submitStorage(ids, status));
    }

}

