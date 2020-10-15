package com.dc.project.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dc.project.system.entity.SysDept;

import java.util.List;

/**
 * 部门档案表 Mapper 接口
 *
 * @author zhuangchongyi
 * @since 2020-08-29
 */
public interface SysDeptDao extends BaseMapper<SysDept> {

    List<SysDept> list(SysDept dept);
}
