package com.dc.project.warehouse.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dc.common.vo.R;
import com.dc.project.warehouse.entity.SysAdjustmentSub;
import com.dc.project.warehouse.service.ISysAdjustmentSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 调整单子表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-09-23
 */
@RestController
@RequestMapping("/warehouse/adjustmentSub")
public class SysAdjustmentSubController {
    @Autowired
    private ISysAdjustmentSubService adjustmentSubService;

    @GetMapping
    public R list(SysAdjustmentSub adjustmentSub) {
        QueryWrapper<SysAdjustmentSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("adjustment_id", adjustmentSub.getAdjustmentId())
                .orderByDesc("create_time");
        return R.success().data(adjustmentSubService.list(queryWrapper));
    }

}

