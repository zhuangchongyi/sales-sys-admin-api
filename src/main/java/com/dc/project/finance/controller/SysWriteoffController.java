package com.dc.project.finance.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.lang.annotation.RepeatSubmit;
import com.dc.common.vo.R;
import com.dc.project.finance.entity.SysWriteoff;
import com.dc.project.finance.service.ISysWriteoffService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 应收核销表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-10-26
 */
@RestController
@RequestMapping("/finance/writeoff")
public class SysWriteoffController {
    @Autowired
    private ISysWriteoffService writeoffService;

    @RequiresPermissions("finance:writeoff:list")
    @GetMapping
    public R page(Page<SysWriteoff> page, SysWriteoff writeoff) {
        return R.success().data(writeoffService.page(page, writeoff));
    }

    @GetMapping("/{id}")
    public R get(@PathVariable Integer id) {
        return R.success().data(writeoffService.getById(id));
    }

    @RepeatSubmit
    @RequiresPermissions("finance:writeoff:add")
    @PostMapping
    public R add(@RequestBody Map<String, Object> formMap) throws InvocationTargetException, IllegalAccessException {
        return R.success().data(writeoffService.insertAndUpdate(formMap));
    }

    @RepeatSubmit
    @RequiresPermissions("finance:writeoff:edit")
    @PutMapping
    public R edit(@RequestBody Map<String, Object> formMap) throws InvocationTargetException, IllegalAccessException {
        return R.success().data(writeoffService.insertAndUpdate(formMap));
    }

    @RepeatSubmit
    @RequiresPermissions("finance:writeoff:delete")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        return R.success().data(writeoffService.delete(id));
    }

    @RepeatSubmit
    @RequiresPermissions("finance:writeoff:audit")
    @PutMapping("/audit")
    public R audit(@RequestBody SysWriteoff writeoff) {
        return R.success().data(writeoffService.audit(writeoff));
    }

}

