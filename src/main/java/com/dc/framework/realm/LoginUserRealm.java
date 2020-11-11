package com.dc.framework.realm;

import com.dc.common.constant.CustomConstant;
import com.dc.common.utils.UserSecurityUtil;
import com.dc.common.vo.UserInfo;
import com.dc.project.common.service.ILoginService;
import com.dc.project.system.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @Author zhuangcy
 * @Description 实现AuthorizingRealm接口用户用户认证
 * @Date 2020/8/14 12:11
 */
@Slf4j(topic = "sys-user")
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
        UserInfo info = loginService.getInfo();
        // 添加角色权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (null == info) {
            throw new AuthenticationException("用户角色权限认证失败");
        }
        Set<String> roles = info.getRoles();
        Set<String> permissions = info.getPermissions();
        authorizationInfo.addRoles(roles);
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 用户授权
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("ShiroRealm 开始用户认证------------");
        if (null == authenticationToken.getPrincipal()) {
            return null;
        }
        // 获取用户信息
        SysUser user = loginService.getUserByUsername(authenticationToken.getPrincipal().toString());
        if (user == null) {
            //登录用户不存在
            throw new UnknownAccountException();
        } else {
            if (!UserSecurityUtil.isAdmin(user.getUserId())) {
                // 登录用户已被禁用
                if (CustomConstant.STOP_STATUS.equals(user.getStartStatus())) {
                    throw new DisabledAccountException();
                } else if (CustomConstant.ORDINARY_USER_TYPE.equals(user.getUserType())) {
                    throw new LockedAccountException();
                }
            }
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
            //设置盐，用来核对密码
            authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
            return authenticationInfo;
        }
    }

}
