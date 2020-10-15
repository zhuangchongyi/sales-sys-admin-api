package com.dc.project.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.system.entity.SysRole;
import org.apache.ibatis.annotations.Param;

/**
 * 角色表 Mapper 接口
 *
 * @author zhuangchongyi
 * @since 2020-08-29
 */
public interface SysRoleDao extends BaseMapper<SysRole> {

    IPage<SysRole> rolePage(Page page, @Param("role") SysRole role);
}
