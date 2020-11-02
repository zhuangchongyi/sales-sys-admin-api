package com.dc.project.finance.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.vo.R;
import com.dc.project.finance.entity.SysWriteoff;
import com.dc.project.finance.service.ISysWriteoffService;
import org.apache.commons.lang3.StringUtils;
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

    @GetMapping
    public R page(Page<SysWriteoff> page,SysWriteoff writeoff) {
        QueryWrapper<SysWriteoff> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(writeoff.getClienteleNum()),"clientele_num",writeoff.getClienteleNum())
                .or().like(StringUtils.isNotEmpty(writeoff.getClienteleName()),"clientele_name",writeoff.getClienteleName());
        queryWrapper.orderByDesc("create_time");
        return R.success().data(writeoffService.page(page, queryWrapper));
    }

    @GetMapping("/{id}")
    public R get(@PathVariable Integer id) {
        return R.success().data(writeoffService.getById(id));
    }

    @PostMapping
    public R add(@RequestBody Map<String,Object> formMap) throws InvocationTargetException, IllegalAccessException {
        return R.success().data(writeoffService.insertAndUpdate(formMap));
    }

    @PutMapping
    public R edit(@RequestBody Map<String,Object> formMap) throws InvocationTargetException, IllegalAccessException {
        return R.success().data(writeoffService.insertAndUpdate(formMap));
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        return R.success().data(writeoffService.delete(id));
    }

    @PutMapping("/audit")
    public R audit(@RequestBody SysWriteoff writeoff) {
        return R.success().data(writeoffService.audit(writeoff));
    }

}

