package com.dc.project.finance.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dc.common.vo.R;
import com.dc.project.finance.entity.SysWriteoffSub;
import com.dc.project.finance.service.ISysWriteoffSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 应收核销明细表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-10-26
 */
@RestController
@RequestMapping("/finance/writeoffSub")
public class SysWriteoffSubController {
    @Autowired
    private ISysWriteoffSubService writeoffSubService;

    @GetMapping
    public R list(SysWriteoffSub writeoffSub) {
        QueryWrapper<SysWriteoffSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("writeoff_id", writeoffSub.getWriteoffId());
        queryWrapper.orderByAsc("writeoff_sub_id");
        return R.success().data(writeoffSubService.list(queryWrapper));
    }


}

