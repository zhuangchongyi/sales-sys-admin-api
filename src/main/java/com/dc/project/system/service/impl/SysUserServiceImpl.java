package com.dc.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.constant.CustomConstant;
import com.dc.common.exception.ServiceException;
import com.dc.common.utils.CodeUtil;
import com.dc.common.utils.UserSecurityUtil;
import com.dc.common.vo.R;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(SysUser sysUser) {
        String username = sysUser.getUsername();
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>().select("user_id")
                .eq("username", username).or().eq("user_num", sysUser.getUserNum());
        SysUser count = this.getOne(queryWrapper, false);
        if (null != count)//不允许重复添加
            throw new ServiceException("员工编码已存在");
        if (StringUtils.isEmpty(username)) {
            sysUser.setUsername(sysUser.getUserNum());
        } else {
            verifyAdmin(username);
            QueryWrapper<SysUser> qw = new QueryWrapper<SysUser>().select("user_id").eq("username", username);
            SysUser one = this.getOne(qw, false);
            if (null != one)//不允许编码重复
                throw new ServiceException("登录账号已存在");
        }
        sysUser.setSalt(new SecureRandomNumberGenerator().nextBytes().toHex());//获取盐值
        sysUser.setPassword(new Md5Hash(CustomConstant.DEFAULT_PASSWORD, sysUser.getSalt(), CustomConstant.ENCRYPTION_NUM).toString());//默认密码为员工编码，加密3次
        return this.save(sysUser);
    }

    private void verifyAdmin(String username) {
        if ("admin".equals(username) || "root".equals(username)) {
            throw new ServiceException("登录账号已存在");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean update(SysUser sysUser) {
        String username = sysUser.getUsername();
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>().select("user_id").eq("username", username);
        SysUser res = this.getOne(queryWrapper, false);
        if (null != res && !res.getUserId().equals(sysUser.getUserId()))//不允许编码重复
            throw new ServiceException("登录账号不能重复");
        queryWrapper = new QueryWrapper<SysUser>().select("user_id").eq("user_num", sysUser.getUserNum());
        res = this.getOne(queryWrapper, false);
        verifyAdmin(username);
        if (null != res && !res.getUserId().equals(sysUser.getUserId()))//不允许编码重复
            throw new ServiceException("编码不能重复");
        return this.updateById(sysUser);
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
        Integer[] roleIds = userRoleService.userRoleList(userId).stream().toArray(Integer[]::new);
        resultMap.put("checkedKeys", roleMenuService.findRoleMenuByRoleIds(roleIds));//角色的菜单
        return resultMap;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addUserRole(SysUser sysUser) {
        Integer userId = sysUser.getUserId();
        if (null == userId) {
            throw new ServiceException("用户不存在");
        }
        Integer[] roleIds = sysUser.getRoleIds();
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<SysUserRole>().eq("user_id", userId);
        SysUserRole one = userRoleService.getOne(queryWrapper, false);
        int length = roleIds.length;
        if (length == 0) {
            throw new ServiceException("未选择角色");
        } else if (null != one) {
            userRoleService.remove(queryWrapper);
        }
        List<SysUserRole> userRoles = Stream.of(roleIds)
                .filter(id -> id != null)
                .map(id -> new SysUserRole().setRoleId(id).setUserId(userId))
                .collect(Collectors.toList());
        if (!userRoles.isEmpty() && !userRoleService.saveBatch(userRoles)) {
            throw new ServiceException("角色修改失败");
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
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
        }

        String username = UserSecurityUtil.getUsername();
        SysUser one = this.getOne(new QueryWrapper<SysUser>().eq("username", username),false);
        if (one == null) {
            throw new ServiceException(R.UNAUTHORIZED, "请重新登陆！");
        }
        String pwd = new Md5Hash(password, one.getSalt(), CustomConstant.ENCRYPTION_NUM).toString();
        if (!pwd.equals(one.getPassword())) {
            throw new ServiceException("原始密码输入错误");
        }
        SysUser sysUser = new SysUser().setUserId(one.getUserId())
                .setPassword(new Md5Hash(newPassword, one.getSalt(), CustomConstant.ENCRYPTION_NUM).toString());
        return this.updateById(sysUser);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateStatus(SysUser sysUser) {
        if (CustomConstant.ORDINARY_USER_TYPE.equals(sysUser.getUserType())) {
            userRoleService.remove(new QueryWrapper<SysUserRole>().eq("user_id", sysUser.getUserId()));
        }
        return this.updateById(sysUser);
    }

    @Override
    public SysUser findByUsername(String username) {
        return baseMapper.findByUsername(username);
    }

    @Override
    public IPage<SysUser> list(Page page, SysUser user) {
        return baseMapper.list(page, user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String resetPassword(Integer userId) {
        SysUser sysUser = this.getById(userId);
        if (null == sysUser) {
            throw new ServiceException("未找到用户");
        }
        String newPassword = CodeUtil.randomCode();
        SysUser user = new SysUser().setUserId(userId)
                .setPassword(new Md5Hash(newPassword, sysUser.getSalt(), CustomConstant.ENCRYPTION_NUM).toString());
        if (!this.updateById(user)) {
            throw new ServiceException("重置失败");
        }
        return newPassword;
    }

}
