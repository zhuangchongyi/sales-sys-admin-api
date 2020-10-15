package com.dc.project.sales.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.sales.entity.SysReturns;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 销售退货主表 Mapper 接口
 *
 * @author zhuangcy
 * @since 2020-10-11
 */
public interface SysReturnsDao extends BaseMapper<SysReturns> {

    IPage<SysReturns> page(Page<SysReturns> page, @Param("sr") SysReturns sysReturns);

    SysReturns get(Integer id);
}
