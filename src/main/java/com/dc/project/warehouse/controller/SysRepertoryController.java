package com.dc.project.warehouse.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.vo.R;
import com.dc.project.warehouse.entity.SysRepertory;
import com.dc.project.warehouse.service.ISysRepertoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 产品现存表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-09-21
 */
@RestController
@RequestMapping("/warehouse/repertory")
public class SysRepertoryController {
    @Autowired
    private ISysRepertoryService repertoryService;

    @GetMapping
    public R page(Page page, SysRepertory repertory) {
        return R.success().data(repertoryService.page(page, repertory));
    }

    @GetMapping("/list")
    public R list(SysRepertory repertory) {
        return R.success().data(repertoryService.list(repertory));
    }


}

