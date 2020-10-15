package com.dc.project.sales.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dc.common.vo.R;
import com.dc.project.sales.entity.SysSignbackSub;
import com.dc.project.sales.service.ISysSignbackSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 销售签回子表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-10-10
 */
@RestController
@RequestMapping("/sales/signbackSub")
public class SysSignbackSubController {
    @Autowired
    private ISysSignbackSubService signbackSubService;

    @GetMapping("/list")
    public R list(SysSignbackSub sysSignbackSub) {
        QueryWrapper<SysSignbackSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        return R.success().data(signbackSubService.list(queryWrapper));
    }

}

