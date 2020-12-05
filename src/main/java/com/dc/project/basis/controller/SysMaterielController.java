package com.dc.project.basis.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.lang.annotation.RepeatSubmit;
import com.dc.common.vo.R;
import com.dc.project.basis.entity.SysMateriel;
import com.dc.project.basis.entity.SysMaterielModel;
import com.dc.project.basis.service.ISysMaterielService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 产品档案表 前端控制器
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
@RestController
@RequestMapping("/basis/materiel")
public class SysMaterielController {
    @Autowired
    private ISysMaterielService materielService;

    @RequiresPermissions("basis:materiel:list")
    @GetMapping
    public R page(Page page, SysMateriel materiel) {
        return R.success().data(materielService.page(page, materiel));
    }

    @GetMapping("/{materielId}")
    public R get(@PathVariable Integer materielId) {
        return R.success().data(materielService.get(materielId));
    }

    @RepeatSubmit
    @RequiresPermissions("basis:materiel:add")
    @PostMapping
    public R add(@RequestBody @Validated SysMateriel materiel) {
        return R.success().data(materielService.insert(materiel));
    }

    @RepeatSubmit
    @RequiresPermissions("basis:materiel:edit")
    @PutMapping
    public R edit(@RequestBody @Validated SysMateriel materiel) {
        return R.success().data(materielService.update(materiel));
    }

    @RepeatSubmit
    @RequiresPermissions("basis:materiel:delete")
    @DeleteMapping("/{materielId}")
    public R delete(@PathVariable Integer materielId) {
        return R.success().data(materielService.removeById(materielId));
    }

    @GetMapping("/list")
    public R list(Page page, SysMateriel materiel) {
        return R.success().data(materielService.list(page, materiel));
    }

    @GetMapping("/models")
    public R models(SysMateriel materiel) {
        return R.success().data(materielService.models(materiel));
    }

    @RepeatSubmit
    @PutMapping("/models")
    public R models(@RequestBody List<SysMaterielModel> models) {
        return R.success().data(materielService.models(models));
    }
}

