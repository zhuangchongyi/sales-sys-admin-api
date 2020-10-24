package com.dc.common.vo;

import com.dc.project.system.entity.SysUser;

import java.util.Set;

public class UserInfo {
    private SysUser user;
    private Set<String> roles;
    private Set<String> permissions;

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
}
