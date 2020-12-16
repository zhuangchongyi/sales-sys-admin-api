package com.dc.project.purchase.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.constant.SalesConstant;
import com.dc.common.lang.annotation.RepeatSubmit;
import com.dc.common.valid.AuditGroup;
import com.dc.common.valid.UpdateGroup;
import com.dc.common.vo.R;
import com.dc.project.purchase.entity.SysPurchaseReturns;
import com.dc.project.purchase.entity.SysPurchaseReturnsSub;
import com.dc.project.purchase.service.ISysPurchaseReturnsService;
import com.dc.common.vo.CommonVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 采购出库 前端控制器
 *
 * @author zhuangcy
 * @since 2020-12-05
 */
@RestController
@RequestMapping("/purchase/outbound")
public class SysPurchaseOutboundController {

    @Autowired
    private ISysPurchaseReturnsService returnsService;

    @RequiresPermissions("purchase:outbound:list")
    @GetMapping
    public R page(Page<SysPurchaseReturns> page, SysPurchaseReturns returns) {
        returns.setStatus(SalesConstant.AUDIT);
        return R.success().data(returnsService.page(page, returns));
    }

    @GetMapping("/{id}")
    public R get(@PathVariable Integer id) {
        return R.success().data(returnsService.get(id));
    }

    @RequiresPermissions("purchase:outbound:edit")
    @RepeatSubmit
    @PutMapping
    public R edit(@RequestBody @Validated(UpdateGroup.class) CommonVo<SysPurchaseReturns, SysPurchaseReturnsSub> vo) {
        return R.success().data(returnsService.edit(vo));
    }

    @RequiresPermissions("purchase:outbound:audit")
    @RepeatSubmit
    @PutMapping("/audit")
    public R audit(@RequestBody @Validated(AuditGroup.class) CommonVo<SysPurchaseReturns, SysPurchaseReturnsSub> vo) {
        return R.success().data(returnsService.auditOutbound(vo));
    }

}

