package com.dc.project.basis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.basis.entity.SysUnits;
import com.dc.project.basis.dao.SysUnitsDao;
import com.dc.project.basis.service.ISysUnitsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 基本单位表 服务实现类
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
@Service
public class SysUnitsServiceImpl extends ServiceImpl<SysUnitsDao, SysUnits> implements ISysUnitsService {

    @Override
    public IPage list(Page page, SysUnits units) {
        return baseMapper.list(page, units);
    }
}
