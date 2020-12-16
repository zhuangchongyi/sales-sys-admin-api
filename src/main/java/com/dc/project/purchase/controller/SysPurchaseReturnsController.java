package com.dc.project.purchase.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.lang.annotation.RepeatSubmit;
import com.dc.common.valid.AuditGroup;
import com.dc.common.valid.InsertGroup;
import com.dc.common.valid.SubmitGroup;
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
 * 采购退货主表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-12-10
 */
@RestController
@RequestMapping("/purchase/returns")
public class SysPurchaseReturnsController {
    @Autowired
    private ISysPurchaseReturnsService returnsService;

    @RequiresPermissions("purchase:returns:list")
    @GetMapping
    public R page(Page<SysPurchaseReturns> page, SysPurchaseReturns returns) {
        return R.success().data(returnsService.page(page, returns));
    }

    @GetMapping("/list")
    public R list(Page<SysPurchaseReturns> page, SysPurchaseReturns returns) {
        return R.success().data(returnsService.page(page, returns));
    }

    @GetMapping("/{id}")
    public R get(@PathVariable Integer id) {
        return R.success().data(returnsService.get(id));
    }

    @RequiresPermissions("purchase:returns:add")
    @RepeatSubmit
    @PostMapping
    public R add(@RequestBody @Validated(InsertGroup.class) CommonVo<SysPurchaseReturns, SysPurchaseReturnsSub> vo) {
        return R.success().data(returnsService.add(vo));
    }

    @RequiresPermissions("purchase:returns:edit")
    @RepeatSubmit
    @PutMapping
    public R edit(@RequestBody @Validated(UpdateGroup.class) CommonVo<SysPurchaseReturns, SysPurchaseReturnsSub> vo) {
        return R.success().data(returnsService.edit(vo));
    }

    @RequiresPermissions("purchase:returns:delete")
    @RepeatSubmit
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        return R.success().data(returnsService.delete(id));
    }

    @RequiresPermissions("purchase:returns:submit")
    @RepeatSubmit
    @PutMapping("/submit")
    public R submit(@RequestBody @Validated(SubmitGroup.class) CommonVo<SysPurchaseReturns, SysPurchaseReturnsSub> vo) {
        return R.success().data(returnsService.submit(vo));
    }

    @RequiresPermissions("purchase:returns:audit")
    @RepeatSubmit
    @PutMapping("/audit")
    public R audit(@RequestBody @Validated(AuditGroup.class) CommonVo<SysPurchaseReturns, SysPurchaseReturnsSub> vo) {
        return R.success().data(returnsService.audit(vo));
    }
}

