package com.dc.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.constant.CustomConstant;
import com.dc.common.exception.ServiceException;
import com.dc.common.utils.CodeUtil;
import com.dc.common.utils.UserSecurityUtils;
import com.dc.project.system.dao.SysUserDao;
import com.dc.project.system.entity.SysMenu;
import com.dc.project.system.entity.SysRole;
import com.dc.project.system.entity.SysUser;
import com.dc.project.system.entity.SysUserRole;
import com.dc.project.system.service.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 人员表 服务实现类
 *
 * @author zhuangchongyi
 * @since 2020-08-28
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements ISysUserService {
    @Autowired
    private ISysRoleService roleService;
    @Autowired
    private ISysUserRoleService userRoleService;
    @Autowired
    private ISysRoleMenuService roleMenuService;
    @Autowired
    private ISysMenuService menuService;


    @Override
    public boolean save(SysUser sysUser) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>().select("user_id")
                .eq("username", sysUser.getUsername()).or().eq("user_num", sysUser.getUserNum());
        int count = baseMapper.selectCount(queryWrapper);
        if (count > 0)//不允许重复添加
            throw new ServiceException("编码或登录账号不唯一");
        if ("admin".equals(sysUser.getUsername())) {
            throw new ServiceException("登录账号不能为管理员账号");
        }
        if (StringUtils.isEmpty(sysUser.getUsername())) {
            sysUser.setUsername(sysUser.getUserNum());
        } else {
            QueryWrapper<SysUser> qw = new QueryWrapper<SysUser>().select("user_id").eq("username", sysUser.getUsername());
            SysUser res = baseMapper.selectOne(qw);
            if (null != res)//不允许编码重复
                throw new ServiceException("登录账号已存在");
        }
        sysUser.setSalt(new SecureRandomNumberGenerator().nextBytes().toHex());//获取盐值
        sysUser.setPassword(new Md5Hash(sysUser.getUserNum(), sysUser.getSalt(), CustomConstant.ENCRYPTION_NUM).toString());//默认密码为员工编码，加密3次
        return this.save(sysUser);
    }

    @Override
    public boolean update(SysUser sysUser) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>().select("user_id").eq("username", sysUser.getUsername());
        SysUser res = baseMapper.selectOne(queryWrapper);
        if (null != res && !res.getUserId().equals(sysUser.getUserId()))//不允许编码重复
            throw new ServiceException("登录账号不能重复");
        queryWrapper = new QueryWrapper<SysUser>().select("user_id").eq("user_num", sysUser.getUserNum());
        res = baseMapper.selectOne(queryWrapper);
        if ("admin".equals(sysUser.getUsername())) {
            throw new ServiceException("不允许修改关键账号");
        }
        if (null != res && !res.getUserId().equals(sysUser.getUserId()))//不允许编码重复
            throw new ServiceException("编码不能重复");
        return baseMapper.updateById(sysUser) > 0;
    }

    @Override
    public Map findUserRoleByUserId(Integer userId) {
        List<SysRole> roleList = roleService.list(new QueryWrapper<SysRole>().orderByAsc("status"));
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("roles", roleList);
        if (null != userId) {
            resultMap.put("roleIds", userRoleService.userRoleList(userId));
        }
        return resultMap;
    }

    @Override
    public Map findUserRoleMenuByUserId(Integer userId) {
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<SysMenu>()
                .eq("status", "0")
                .orderByAsc("menu_id")
                .orderByAsc("parent_id");
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("menus", menuService.buildMenuTreeSelect(menuService.list(queryWrapper)));//所有菜单
        Integer[] roleIds = userRoleService.userRoleList(userId).toArray(new Integer[]{});
        resultMap.put("checkedKeys", roleMenuService.findRoleMenuByRoleIds(roleIds));//角色的菜单
        return resultMap;
    }

    @Transactional
    @Override
    public boolean addUserRole(SysUser sysUser) {
        if (null == sysUser.getUserId()) {
            throw new ServiceException("用户不存在");
        }
        Integer[] roleIds = sysUser.getRoleIds();
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<SysUserRole>().eq("user_id", sysUser.getUserId());
        int count = userRoleService.count(queryWrapper);
        int length = roleIds.length;
        if (length == 0 && count == 0) {
            throw new ServiceException("未选择角色");
        }
        userRoleService.remove(queryWrapper);
        for (int i = 0; i < length; i++) {
            SysUserRole userRole = new SysUserRole();
            userRole.setRoleId(roleIds[i]);
            userRole.setUserId(sysUser.getUserId());
            boolean row = userRoleService.save(userRole);
            if (!row) {
                throw new ServiceException("角色修改失败");
            }
        }
        return true;
    }

    @Override
    public boolean changePassword(Map<String, Object> formMap) {
        String password = (String) formMap.get("password");
        String newPassword = (String) formMap.get("newPassword");
        String checkPassword = (String) formMap.get("checkPassword");
        if (StringUtils.isEmpty(password)) {
            throw new ServiceException("原密码不能为空");
        } else if (StringUtils.isEmpty(newPassword)) {
            throw new ServiceException("新密码不能为空");
        } else if (StringUtils.isEmpty(checkPassword)) {
            throw new ServiceException("确认密码不能为空");
        } else if (!StringUtils.equals(newPassword, checkPassword)) {
            throw new ServiceException("两次输入密码不一致!");
//        } else if (StringUtils.length(newPassword)<6) {
//            throw new ServiceException("密码长度不能小于6位");
        }

        String username = UserSecurityUtils.getUsername();
        SysUser one = this.getOne(new QueryWrapper<SysUser>().eq("username", username));
        if (one == null) {
            throw new ServiceException("请重新登陆！");
        }
        String pwd = new Md5Hash(password, one.getSalt(), CustomConstant.ENCRYPTION_NUM).toString();
        if (!pwd.equals(one.getPassword())) {
            throw new ServiceException("原密码输入错误");
        }
        SysUser sysUser = new SysUser().setUserId(one.getUserId())
                .setPassword(new Md5Hash(newPassword, one.getSalt(), CustomConstant.ENCRYPTION_NUM).toString());
        return this.updateById(sysUser);
    }

    @Override
    public SysUser findRoleByUsername(String username) {
        return baseMapper.findRoleByUsername(username);
    }

    @Transactional
    @Override
    public boolean updateStatus(SysUser sysUser) {
        if (CustomConstant.ORDINARY_USER_TYPE.equals(sysUser.getUserType())) {
            userRoleService.remove(new QueryWrapper<SysUserRole>().eq("user_id", sysUser.getUserId()));
        }
        baseMapper.updateById(sysUser);
        return false;
    }

    @Override
    public SysUser getUserByUsername(String username) {
        return baseMapper.getUserByUsername(username);
    }

    @Override
    public SysUser findByUsername(String username) {
        return baseMapper.findByUsername(username);
    }

    @Override
    public IPage<SysUser> list(Page page, SysUser user) {
        return baseMapper.list(page, user);
    }

    @Override
    public String resetPassword(Integer userId) {
        SysUser sysUser = this.getById(userId);
        if (null == sysUser) {
            throw new ServiceException("未找到用户");
        }
        String newPassword = CodeUtil.randomCode();
        SysUser user = new SysUser().setUserId(userId)
                .setPassword(new Md5Hash(newPassword, sysUser.getSalt(), CustomConstant.ENCRYPTION_NUM).toString());
        int row = baseMapper.updateById(user);
        if (row == 0) {
            throw new ServiceException("重置失败");
        }
        return newPassword;
    }

}
