package com.dc.project.monitor.controller;


import com.dc.project.monitor.service.ISysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * 系统操作日志表 前端控制器
 *
 * @author zhuangcy
 * @since 2020-11-09
 */
@RestController
@RequestMapping("/monitor/operLog")
public class SysOperLogController {

    @Autowired
    private ISysOperLogService operLogService;

}

