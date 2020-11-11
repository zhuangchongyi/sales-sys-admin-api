package com.dc.project.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dc.project.system.entity.SysRole;
import com.dc.project.system.entity.SysUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 角色表 服务类
 *
 * @author zhuangchongyi
 * @since 2020-08-29
 */
public interface ISysRoleService extends IService<SysRole> {

    IPage<SysRole> list(Page page, SysRole sysRole);

    boolean update(SysRole sysRole);

    boolean delete(Integer roleId);

    Page<SysUser> roleUserListPage(Page<SysUser> page, Integer roleId);

    boolean addRoleMenu(SysRole sysRole);

    Map<String, Object> getDataScope(Integer id);

    @Transactional(rollbackFor = Exception.class)
    boolean updateDataScope(SysRole sysRole);
}
