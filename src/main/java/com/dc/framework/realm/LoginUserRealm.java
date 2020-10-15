package com.dc.framework.realm;

import com.dc.common.constant.CustomConstant;
import com.dc.common.utils.UserSecurityUtils;
import com.dc.project.system.entity.SysUser;
import com.dc.project.common.service.ILoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Set;

/**
 * @Author zhuangcy
 * @Description 实现AuthorizingRealm接口用户用户认证
 * @Date 2020/8/14 12:11
 */
@Slf4j
public class LoginUserRealm extends AuthorizingRealm {
    @Autowired
    private ILoginService loginService;

    /**
     * 角色权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("ShiroRealm 开始角色认证------------");
        // 查询用户权限明细
        Map<String, Object> map = loginService.getInfo();
        // 添加角色权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (map.isEmpty()) {
            throw new AuthenticationException("用户角色权限认证失败");
        }
        Set<String> roles = (Set<String>) map.get("roles");
        Set<String> permissions = (Set<String>) map.get("permissions");
        authorizationInfo.addRoles(roles);
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 用户认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("ShiroRealm 开始用户认证------------");
        if (null == authenticationToken.getPrincipal()) {
            return null;
        }
        // 获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        SysUser user = loginService.getUserByUsername(name);
        if (user == null) {
            //登录用户不存在
            throw new UnknownAccountException();
        } else {
            if (!UserSecurityUtils.isAdmin(user.getUserId())) {
                // 登录用户已被禁用
                if (CustomConstant.STOP_STATUS.equals(user.getStartStatus())) {
                    throw new DisabledAccountException();
                }
            }
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
            //设置盐，用来核对密码
            authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
            return authenticationInfo;
        }
    }

}
