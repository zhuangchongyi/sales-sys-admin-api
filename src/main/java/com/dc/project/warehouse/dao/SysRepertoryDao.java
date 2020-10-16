package com.dc.project.warehouse.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.warehouse.entity.SysRepertory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 产品现存表 Mapper 接口
 *
 * @author zhuangcy
 * @since 2020-09-21
 */
public interface SysRepertoryDao extends BaseMapper<SysRepertory> {

    IPage<SysRepertory> page(Page page, @Param("sr") SysRepertory sysRepertory);

    List<SysRepertory> list(SysRepertory sysRepertory);
}
