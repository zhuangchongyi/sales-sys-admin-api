package com.dc.project.basis.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.lang.annotation.RepeatSubmit;
import com.dc.common.exception.ServiceException;
import com.dc.common.vo.R;
import com.dc.project.basis.entity.SysClientele;
import com.dc.project.basis.service.ISysClienteleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 部门表 前端控制器
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
@RestController
@RequestMapping("/basis/clientele")
public class SysClienteleController {
    @Autowired
    private ISysClienteleService clienteleService;

    @RequiresPermissions("basis:clientele:list")
    @GetMapping
    public R page(Page<SysClientele> page, SysClientele clientele) {
        return R.success().data(clienteleService.list(page, clientele));
    }

    @GetMapping("/list")
    public R list(Page<SysClientele> page, SysClientele clientele) {
        return R.success().data(clienteleService.list(page, clientele));
    }

    @GetMapping("/{clienteleId}")
    public R get(@PathVariable Integer clienteleId) {
        return R.success().data(clienteleService.get(clienteleId));
    }

    @RepeatSubmit
    @RequiresPermissions("basis:clientele:add")
    @PostMapping
    public R add(@RequestBody @Validated SysClientele sysClientele) {
        SysClientele count = clienteleService.getOne(new QueryWrapper<SysClientele>().select("clientele_id")
                .eq("clientele_num", sysClientele.getClienteleNum()), false);
        if (count != null)
            throw new ServiceException("编码不允许重复");
        return R.success().data(clienteleService.save(sysClientele));
    }

    @RepeatSubmit
    @RequiresPermissions("basis:clientele:edit")
    @PutMapping
    public R edit(@RequestBody @Validated SysClientele sysClientele) {
        SysClientele one = clienteleService.getOne(new QueryWrapper<SysClientele>().select("clientele_id")
                .eq("clientele_num", sysClientele.getClienteleNum()), false);
        if (null != one && !one.getClienteleId().equals(sysClientele.getClienteleId()))
            throw new ServiceException("编码不允许重复");
        return R.success().data(clienteleService.updateById(sysClientele));
    }

    @RepeatSubmit
    @RequiresPermissions("basis:clientele:delete")
    @DeleteMapping("/{clienteleId}")
    public R delete(@PathVariable Integer clienteleId) {
        return R.success().data(clienteleService.removeById(clienteleId));
    }

    @GetMapping("/getClientele")
    public R getClientele(Integer clienteleId) {
        if (null == clienteleId) return R.error();
        return R.success().data(clienteleService
                .getOne(new QueryWrapper<SysClientele>().select("clientele_id,clientele_num,clientele_name")
                        .eq("clientele_id", clienteleId), false));
    }

}

