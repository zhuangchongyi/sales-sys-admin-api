package com.dc.project.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.project.system.entity.SysRole;

/**
 * 角色表 服务类
 *
 * @author zhuangchongyi
 * @since 2020-08-29
 */
public interface ISysRoleService extends IService<SysRole> {

    IPage<SysRole> list(Page page, SysRole sysRole);

}
