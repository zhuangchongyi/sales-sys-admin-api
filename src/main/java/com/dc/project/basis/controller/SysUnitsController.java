package com.dc.project.basis.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.lang.annotation.RepeatSubmit;
import com.dc.common.exception.ServiceException;
import com.dc.common.vo.R;
import com.dc.project.basis.entity.SysUnits;
import com.dc.project.basis.service.ISysUnitsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 基本单位表 前端控制器
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
@RestController
@RequestMapping("/basis/units")
public class SysUnitsController {
    @Autowired
    private ISysUnitsService unitsService;

    @RequiresPermissions("basis:units:list")
    @GetMapping
    public R list(Page<SysUnits> page, SysUnits units) {
        return R.success().data(unitsService.list(page, units));
    }

    @GetMapping("/{unitsId}")
    public R get(@PathVariable Integer unitsId) {
        QueryWrapper<SysUnits> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(SysUnits.class, info -> !info.getColumn().equals("create_time") && !info.getColumn().equals("create_by"))
                .eq("units_id", unitsId);
        return R.success().data(unitsService.getOne(queryWrapper, false));
    }

    @RepeatSubmit
    @RequiresPermissions("basis:units:add")
    @PostMapping
    public R add(@RequestBody @Validated SysUnits units) {
        SysUnits count = unitsService.getOne(new QueryWrapper<SysUnits>()
                .select("units_id").eq("units_num", units.getUnitsNum()), false);
        if (count != null) throw new ServiceException("编码不允许重复");
        return R.success().data(unitsService.save(units));
    }

    @RepeatSubmit
    @RequiresPermissions("basis:units:edit")
    @PutMapping
    public R edit(@RequestBody @Validated SysUnits units) {
        SysUnits res = unitsService.getOne(new QueryWrapper<SysUnits>()
                .select("units_id").eq("units_num", units.getUnitsNum()), false);
        if (null != res && !res.getUnitsId().equals(units.getUnitsId())) throw new ServiceException("编码不允许重复");
        return R.success().data(unitsService.updateById(units));
    }

    @RepeatSubmit
    @RequiresPermissions("basis:units:delete")
    @DeleteMapping("/{unitsId}")
    public R delete(@PathVariable Integer unitsId) {
        return R.success().data(unitsService.removeById(unitsId));
    }

}

