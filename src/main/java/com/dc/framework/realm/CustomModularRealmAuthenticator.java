package com.dc.framework.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.Collection;

/**
 * @author zhuangcy
 * @date 2020/11/16 15:39
 * @description 自定义多realm登录策略
 */
public class CustomModularRealmAuthenticator extends ModularRealmAuthenticator {
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 判断getRealm是否为空
        assertRealmsConfigured();
        // 获取所有realm
        Collection<Realm> realms = getRealms();

        CustomUserToken token = (CustomUserToken) authenticationToken;
        if (null != token) {
            for (Realm realm : realms) {
                if (token.getLoginType().equals(realm.getName())) {
                    return super.doSingleRealmAuthentication(realm, authenticationToken);
                }
            }
        }

        return super.doMultiRealmAuthentication(realms, authenticationToken);
    }
}
