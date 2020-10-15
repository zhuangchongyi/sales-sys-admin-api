package com.dc.project.sales.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dc.common.vo.R;
import com.dc.project.sales.entity.SysReturnsSub;
import com.dc.project.sales.service.ISysReturnsSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 销售退货子表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-10-11
 */
@RestController
@RequestMapping("/sales/returnsSub")
public class SysReturnsSubController {
    @Autowired
    private ISysReturnsSubService returnsSubService;

    @GetMapping("/list")
    public R list(SysReturnsSub sysReturnsSub) {
        QueryWrapper<SysReturnsSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("returns_id", sysReturnsSub.getReturnsId());
        queryWrapper.orderByDesc("sub_id");
        return R.success().data(returnsSubService.list(queryWrapper));
    }
}

