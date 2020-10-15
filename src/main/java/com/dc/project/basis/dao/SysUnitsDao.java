package com.dc.project.basis.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.basis.entity.SysUnits;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 基本单位表 Mapper 接口
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
public interface SysUnitsDao extends BaseMapper<SysUnits> {

    IPage list(Page page, @Param("units") SysUnits units);
}
