package com.dc.project.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.project.system.dao.SysRoleDao;
import com.dc.project.system.entity.SysRole;
import com.dc.project.system.service.ISysRoleService;
import org.springframework.stereotype.Service;

/**
 * 角色表 服务实现类
 *
 * @author zhuangchongyi
 * @since 2020-08-29
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements ISysRoleService {

    @Override
    public IPage<SysRole> list(Page page, SysRole sysRole) {
        return baseMapper.rolePage(page, sysRole);
    }

}
