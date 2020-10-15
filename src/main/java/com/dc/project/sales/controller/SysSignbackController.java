package com.dc.project.sales.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.vo.R;
import com.dc.project.sales.entity.SysSignback;
import com.dc.project.sales.service.ISysSignbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 销售签回主表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-10-10
 */
@RestController
@RequestMapping("/sales/signback")
public class SysSignbackController {
    @Autowired
    private ISysSignbackService signbackService;

    @GetMapping
    public R page(Page page, SysSignback sysSignback) {
        return R.success().data(signbackService.page(page, sysSignback));
    }

    @GetMapping("/{id}")
    public R get(@PathVariable Integer id) {
        return R.success().data(signbackService.get(id));
    }

    @PutMapping
    public R edit(@RequestBody Map<String, Object> formMap) throws InvocationTargetException, IllegalAccessException {
        return R.success().data(signbackService.edit(formMap));
    }

    @PutMapping("/audit")
    public R audit(@RequestBody SysSignback sysSignback) {
        return R.success().data(signbackService.audit(sysSignback));
    }

}

