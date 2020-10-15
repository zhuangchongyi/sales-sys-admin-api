package com.dc.project.sales.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.sales.entity.SysSignback;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 销售签回主表 Mapper 接口
 *
 * @author zhuangcy
 * @since 2020-10-10
 */
public interface SysSignbackDao extends BaseMapper<SysSignback> {

    IPage<SysSignback> page(Page<SysSignback> page, @Param("sign") SysSignback sysSignback);

    SysSignback get(Integer id);
}
