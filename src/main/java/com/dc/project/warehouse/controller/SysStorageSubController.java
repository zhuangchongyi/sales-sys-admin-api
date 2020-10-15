package com.dc.project.warehouse.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dc.common.vo.R;
import com.dc.project.warehouse.entity.SysStorageSub;
import com.dc.project.warehouse.service.ISysStorageSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 产品入库子表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-09-22
 */
@RestController
@RequestMapping("/warehouse/storageSub")
public class SysStorageSubController {

    @Autowired
    private ISysStorageSubService storageSubService;

    @GetMapping
    public R list(SysStorageSub storageSub) {
        QueryWrapper<SysStorageSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("storage_id", storageSub.getStorageId())
                .orderByDesc("create_time");
        return R.success().data(storageSubService.list(queryWrapper));
    }

}

