package com.dc.project.finance.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.lang.annotation.RepeatSubmit;
import com.dc.common.vo.CommonVo;
import com.dc.common.vo.R;
import com.dc.project.finance.entity.SysPayable;
import com.dc.project.finance.service.ISysPayableService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 财务应付款主表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-12-11
 */
@RestController
@RequestMapping("/finance/payable")
public class SysPayableController {
    @Autowired
    private ISysPayableService payableService;

    @RequiresPermissions("finance:payable:list")
    @GetMapping
    public R page(Page<SysPayable> page, SysPayable payable) {
        return R.success().data(payableService.page(page, payable));
    }

    @GetMapping("/{id}")
    public R get(@PathVariable Integer id) {
        return R.success().data(payableService.get(id));
    }

    @RepeatSubmit
    @RequiresPermissions("finance:payable:add")
    @PostMapping
    public R add(@RequestBody SysPayable payable) {
        return R.success().data(payableService.add(payable));
    }

    @RepeatSubmit
    @RequiresPermissions("finance:payable:edit")
    @PutMapping
    public R edit(@RequestBody SysPayable payable) {
        return R.success().data(payableService.edit(payable));
    }

    @RepeatSubmit
    @RequiresPermissions("finance:payable:delete")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        return R.success().data(payableService.delete(id));
    }

    @RepeatSubmit
    @RequiresPermissions("finance:payable:submit")
    @PutMapping("/submit/{status}")
    public R submit(@RequestBody CommonVo vo) {
        return R.success().data(payableService.submit(vo));
    }

    @RepeatSubmit
    @RequiresPermissions("finance:payable:audit")
    @PutMapping("/audit")
    public R audit(@RequestBody SysPayable payable) {
        return R.success().data(payableService.audit(payable));
    }

}

