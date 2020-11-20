package com.dc.framework.realm;

import com.dc.common.vo.OpenUser;
import com.dc.project.system.entity.SysUser;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Collection;

/**
 * @author zhuangcy
 * @date 2020/11/16 17:48
 * @description 自定义角色权限授权
 */
public class CustomModularRealmAuthorizer extends ModularRealmAuthorizer {
    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        Collection<Realm> realms = getRealms();
        Object principal = principals.getPrimaryPrincipal();
        for (Realm realm : realms) {
            if (principal instanceof SysUser && LoginType.LOGIN_TYPE_SYSTEM.equals(realm.getName())) {
                SystemUserRealm r = (SystemUserRealm) realm;
                return r.isPermitted(principals, permission);
            } else if (principal instanceof OpenUser && LoginType.LOGIN_TYPE_WX.equals(realm.getName())) {
                WxUserRealm r = (WxUserRealm) realm;
                return r.isPermitted(principals, permission);
            }
        }
        // return super.isPermitted(principals, permission);
        return false;
    }
}
