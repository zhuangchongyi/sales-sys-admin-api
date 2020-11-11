package com.dc.project.monitor.service.impl;

import com.dc.project.monitor.entity.SysOperLog;
import com.dc.project.monitor.dao.SysOperLogDao;
import com.dc.project.monitor.service.ISysOperLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 状态操作日志表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-11-09
 */
@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogDao, SysOperLog> implements ISysOperLogService {

}
