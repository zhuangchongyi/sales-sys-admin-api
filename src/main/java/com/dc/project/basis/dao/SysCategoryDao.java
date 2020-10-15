package com.dc.project.basis.dao;

import com.dc.project.basis.entity.SysCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 自定义类别表 Mapper 接口
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
public interface SysCategoryDao extends BaseMapper<SysCategory> {

    List<SysCategory> list(SysCategory sysCategory);
}
