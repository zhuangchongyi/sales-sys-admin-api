package com.dc.project.warehouse.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.common.exception.ServiceException;
import com.dc.common.vo.R;
import com.dc.project.warehouse.entity.SysRepertory;
import com.dc.project.warehouse.service.ISysRepertoryService;
import org.apache.commons.lang3.StringUtils;
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
        return R.success().data(repertoryService.page(page, getRepertoryQueryWrapper(repertory)));
    }

    @GetMapping("/list")
    public R list(SysRepertory repertory) {
        return R.success().data(repertoryService.list(getRepertoryQueryWrapper(repertory)));
    }

    private QueryWrapper<SysRepertory> getRepertoryQueryWrapper(SysRepertory repertory) {
        if (null == repertory.getWarehouseId()) {
            throw new ServiceException("未选择仓库");
        }
        QueryWrapper<SysRepertory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("warehouse_id", repertory.getWarehouseId());
        queryWrapper.like(StringUtils.isNotEmpty(repertory.getMaterielNum()), "materiel_num", repertory.getMaterielNum())
                .or().like(StringUtils.isNotEmpty(repertory.getMaterielName()), "materiel_name", repertory.getMaterielName())
                .or().like(StringUtils.isNotEmpty(repertory.getSpecification()), "specification", repertory.getSpecification())
                .or().like(StringUtils.isNotEmpty(repertory.getNeedTorque()), "need_torque", repertory.getNeedTorque())
                .or().like(StringUtils.isNotEmpty(repertory.getOutTorque()), "out_torque", repertory.getOutTorque())
                .or().like(StringUtils.isNotEmpty(repertory.getModelName()), "model_name", repertory.getModelName());
        queryWrapper.orderByDesc("create_time");
        return queryWrapper;
    }

}

