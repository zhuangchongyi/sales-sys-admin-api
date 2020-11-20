package com.dc.framework.realm;

import com.dc.project.basis.service.ISysClienteleService;
import com.dc.common.vo.OpenUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author zhuangcy
 * @Description 微信用户认证
 * @Date 2020/8/14 12:11
 */
@Slf4j(topic = "sys-user")
public class WxUserRealm extends AuthorizingRealm {
    @Autowired
    private ISysClienteleService clienteleService;

    /**
     * 角色权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("WX 开始角色认证------------");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();
        roles.add("wx");
        Set<String> permissions = new HashSet<>();
        permissions.add("open:*:*");
        authorizationInfo.addRoles(roles);
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 用户授权
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("WX 开始用户认证------------");
        Object principal = authenticationToken.getPrincipal();// 用户名
        if (null == principal) {
            return null;
        }

//        QueryWrapper<SysClientele> queryWrapper = new QueryWrapper<>();
//        queryWrapper.lambda().eq(SysClientele::getClienteleNum, principal);
//        SysClientele one = clienteleService.getOne(queryWrapper, false);
//        if (null == one) {
//            throw new UnknownAccountException();
//        } else {
//            Object credentials = authenticationToken.getCredentials();//密码
//        }
        OpenUser user = new OpenUser();
        user.setUsername(principal.toString());
        user.setPassword(authenticationToken.getPrincipal().toString());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, authenticationToken.getPrincipal(), getName());
        return authenticationInfo;
    }

    @Override
    public String getName() {
        return LoginType.LOGIN_TYPE_WX;
    }
}
