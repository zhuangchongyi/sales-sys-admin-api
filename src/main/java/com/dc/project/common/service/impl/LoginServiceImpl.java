package com.dc.project.common.service.impl;

import com.dc.common.utils.UserSecurityUtils;
import com.dc.common.vo.LoginUser;
import com.dc.common.vo.UserInfo;
import com.dc.project.common.service.ILoginService;
import com.dc.project.system.entity.SysUser;
import com.dc.project.system.service.ISysMenuService;
import com.dc.project.system.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 登录接口实现类
 *
 * @author zhuangchongyi
 * @since 2020-08-28
 */
@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysMenuService menuService;

    @Override
    public Map login(LoginUser loginUser) {
        UsernamePasswordToken token = new UsernamePasswordToken(loginUser.getUsername(), loginUser.getPassword());
        SecurityUtils.getSubject().login(token);
        Map<String, Object> resultMap = new HashMap<>();
        String sessionId = SecurityUtils.getSubject().getSession().getId().toString();
        resultMap.put("token", sessionId);
        resultMap.put("loginTime", new Date());
//        AsyncManager.me().execute(AsyncFactory.testTimerTask("用户登录成功"));
        return resultMap;
    }

    @Override
    public SysUser getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }

    @Override
    public UserInfo getInfo() {
        SysUser sysUser = userService.findByUsername(UserSecurityUtils.getUsername());
        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();
        if (UserSecurityUtils.isAdmin(null)) {
            roles.add("admin");
            permissions.add("*:*:*");
        } else {
            roles = sysUser.getRoles().stream().map(role -> role.getRoleNum()).collect(Collectors.toSet());
            permissions = menuService.getMenuPermission(sysUser.getUserId());
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUser(sysUser);
        userInfo.setRoles(roles);
        userInfo.setPermissions(permissions);
        return userInfo;
    }
}
