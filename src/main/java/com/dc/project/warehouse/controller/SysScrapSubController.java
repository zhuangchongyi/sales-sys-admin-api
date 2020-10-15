package com.dc.project.warehouse.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dc.common.vo.R;
import com.dc.project.warehouse.entity.SysScrapSub;
import com.dc.project.warehouse.service.ISysScrapSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * 报废单子表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-09-25
 */
@RestController
@RequestMapping("/warehouse/scrapSub")
public class SysScrapSubController {
    @Autowired
    private ISysScrapSubService scrapSubService;

    @GetMapping
    public R list(SysScrapSub sysScrapSub) {
        QueryWrapper<SysScrapSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("scrap_id", sysScrapSub.getScrapId())
                .orderByDesc("create_time");
        return R.success().data(scrapSubService.list(queryWrapper));
    }

}

