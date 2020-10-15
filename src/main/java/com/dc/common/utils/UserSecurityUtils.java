package com.dc.common.utils;

import com.dc.project.system.entity.SysUser;
import org.apache.shiro.SecurityUtils;

/**
 * @Author zhuangcy
 * @Description 获取认证用户工具类
 * @Date 2020/9/12 12:01
 */
public class UserSecurityUtils {

    /**
     * 获取用户名称
     **/
    public static String getUsername() {
        if (getUser() == null)
            return null;
        return getUser().getUsername();
    }

    /**
     * 获取用户
     **/
    public static SysUser getUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 判断是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Integer userId) {
        return (userId != null && 1L == userId) || "admin".equals(getUsername());
    }


}
