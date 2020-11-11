package com.dc.project.system.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc.project.system.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 人员表 Mapper 接口
 *
 * @author zhuangchongyi
 * @since 2020-08-28
 */
public interface SysUserDao extends BaseMapper<SysUser> {

    SysUser findByUsername(String username);

    IPage<SysUser> list(Page page, @Param("user") SysUser user);

}
