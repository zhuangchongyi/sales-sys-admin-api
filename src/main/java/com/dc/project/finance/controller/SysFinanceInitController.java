package com.dc.project.finance.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.vo.R;
import com.dc.project.finance.entity.SysFinanceInit;
import com.dc.project.finance.service.ISysFinanceInitService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 财务初始化表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-10-19
 */
@RestController
@RequestMapping("/finance/init")
public class SysFinanceInitController {

    @Autowired
    private ISysFinanceInitService financeInitService;

    @GetMapping
    public R page(Page page, SysFinanceInit financeInit) {
        QueryWrapper<SysFinanceInit> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(financeInit.getClienteleNum()), "clientele_num", financeInit.getClienteleNum())
                .or().like(StringUtils.isNotEmpty(financeInit.getClienteleName()), "clientele_name", financeInit.getClienteleName());
        queryWrapper.orderByDesc("create_time");
        return R.success().data(financeInitService.page(page, queryWrapper));
    }

    @GetMapping("/{id}")
    public R get(@PathVariable Integer id) {
        QueryWrapper<SysFinanceInit> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(SysFinanceInit.class,
                info -> !info.getColumn().equals("create_time") && !info.getColumn().equals("update_time"));
        queryWrapper.eq("init_id", id);
        return R.success().data(financeInitService.getOne(queryWrapper));
    }

    @PostMapping
    public R add(@RequestBody Map<String, Object> formMap) throws InvocationTargetException, IllegalAccessException {
        return R.success().data(financeInitService.saveAndUpdate(formMap));
    }

    @PutMapping
    public R edit(@RequestBody Map<String, Object> formMap) throws InvocationTargetException, IllegalAccessException {
        return R.success().data(financeInitService.saveAndUpdate(formMap));
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        return R.success().data(financeInitService.delete(id));
    }
}

