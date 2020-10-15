package com.dc.project.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.project.system.dao.SysUserRoleDao;
import com.dc.project.system.entity.SysUser;
import com.dc.project.system.entity.SysUserRole;
import com.dc.project.system.service.ISysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户和角色关联表 服务实现类
 *
 * @author zhuangchongyi
 * @since 2020-08-31
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements ISysUserRoleService {

    @Override
    public Page<SysUser> roleUserListPage(Page<SysUser> page, Integer roleId) {
        return baseMapper.roleUserListPage(page, roleId);
    }

    @Override
    public List<Integer> userRoleList(Integer userId) {
        return baseMapper.findUserRoleByUserId(userId);
    }
}
