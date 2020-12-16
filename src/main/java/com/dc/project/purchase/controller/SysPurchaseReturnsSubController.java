package com.dc.project.purchase.controller;


import com.dc.common.vo.R;
import com.dc.project.purchase.entity.SysPurchaseReturnsSub;
import com.dc.project.purchase.service.ISysPurchaseReturnsSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 采购退货单子表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-12-10
 */
@RestController
@RequestMapping("/purchase/returnsSub")
public class SysPurchaseReturnsSubController {
    @Autowired
    private ISysPurchaseReturnsSubService returnsSubService;

    @GetMapping("/list")
    public R list(SysPurchaseReturnsSub sub) {
        return R.success().data(returnsSubService.list(sub));
    }

}

