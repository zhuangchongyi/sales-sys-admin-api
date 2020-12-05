package com.dc.project.purchase.controller;


import com.dc.common.vo.R;
import com.dc.project.purchase.entity.SysPurchaseSignSub;
import com.dc.project.purchase.service.ISysPurchaseSignSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 采购到货单子表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-12-05
 */
@RestController
@RequestMapping("/purchase/signSub")
public class SysPurchaseSignSubController {
    @Autowired
    private ISysPurchaseSignSubService signSubService;

    @GetMapping("/list")
    public R list(SysPurchaseSignSub sub) {
        return R.success().data(signSubService.list(sub));
    }

}

