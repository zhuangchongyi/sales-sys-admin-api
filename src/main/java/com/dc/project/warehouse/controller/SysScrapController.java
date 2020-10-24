package com.dc.project.warehouse.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.vo.R;
import com.dc.project.warehouse.entity.SysScrap;
import com.dc.project.warehouse.service.ISysScrapService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 报废单主表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-09-25
 */
@RestController
@RequestMapping("/warehouse/scrap")
public class SysScrapController {
    @Autowired
    private ISysScrapService scrapService;

    @RequiresPermissions(value = {"warehouse:scrap:list"})
    @GetMapping
    public R page(Page page, SysScrap scrap) {
        QueryWrapper<SysScrap> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(scrap.getWarehouseName()), "warehouse_name", scrap.getWarehouseName())
                .or().like(StringUtils.isNotEmpty(scrap.getWarehouseNum()), "warehouse_num", scrap.getWarehouseNum())
                .orderByDesc("create_time");
        return R.success().data(scrapService.page(page, queryWrapper));
    }

    @RequiresPermissions(value = {"warehouse:scrap:add", "warehouse:scrap:edit"}, logical = Logical.OR)
    @PostMapping
    public R addAndUpdate(@RequestBody Map formMap) throws Exception {
        return R.success().data(scrapService.addAndUpdate(formMap));
    }

    @RequiresPermissions(value = {"warehouse:scrap:delete"})
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        return R.success().data(scrapService.delete(id));
    }

    @GetMapping("/{id}")
    public R get(@PathVariable Integer id) {
        return R.success().data(scrapService.getById(id));
    }

    @RequiresPermissions(value = {"warehouse:scrap:submit"})
    @PutMapping("/submit/{status}")
    public R submit(@RequestBody Integer[] ids, @PathVariable String status) {
        return R.success().data(scrapService.submit(ids, status));
    }

    @RequiresPermissions(value = {"warehouse:scrap:audit"})
    @PutMapping("/audit")
    public R audit(@RequestBody SysScrap scrap) {
        return R.success().data(scrapService.audit(scrap));
    }

}

