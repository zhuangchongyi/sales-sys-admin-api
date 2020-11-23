package com.dc.project.common.service.impl;

import com.dc.common.constant.CustomConstant;
import com.dc.common.exception.ServiceException;
import com.dc.common.utils.Base64;
import com.dc.common.utils.IdUtil;
import com.dc.common.utils.UserSecurityUtil;
import com.dc.common.utils.VerifyCodeUtil;
import com.dc.common.vo.LoginUser;
import com.dc.common.vo.R;
import com.dc.common.vo.UserInfo;
import com.dc.framework.async.AsyncFactory;
import com.dc.framework.async.AsyncManager;
import com.dc.framework.config.properties.SmsProperties;
import com.dc.framework.realm.CustomUserToken;
import com.dc.project.common.service.ILoginService;
import com.dc.project.system.entity.SysUser;
import com.dc.project.system.service.ISysMenuService;
import com.dc.project.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 登录接口实现类
 *
 * @author zhuangchongyi
 * @since 2020-08-28
 */
@Slf4j
@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysMenuService menuService;
    @Resource
    private SessionDAO sessionDAO;
    @Autowired
    private SmsProperties config;
    //验证码缓存
    //private CustomLRUCache<String, String> captchaCache = new CustomLRUCache(50);
    @Autowired
    private EhCacheManager ehCacheManager;

    @Override
    public Map<String, Object> login(LoginUser loginUser) {
        if (config.isCaptchaCache()) {
            String code = loginUser.getCode();
            String key = CustomConstant.CAPTCHA_CODE_KEY + loginUser.getUuid();
            //String cacheCode = captchaCache.get(key);
            //captchaCache.remove(key);
            Cache<String, String> captchaCache = ehCacheManager.getCache(CustomConstant.CAPTCHA_CACHE_NAME);
            String cacheCode = captchaCache.get(key);
            captchaCache.remove(key);
            if (StringUtils.isEmpty(code) || StringUtils.isEmpty(cacheCode) || !code.toLowerCase().equals(cacheCode.toLowerCase())) {
                throw new ServiceException(R.AUTH_FAIL_CODE, "验证码有误，请重新输入");
            }
        }
        this.verifyUserSession(loginUser.getUsername());
        CustomUserToken token = new CustomUserToken(loginUser.getUsername(), loginUser.getPassword());
        SecurityUtils.getSubject().login(token);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("token", SecurityUtils.getSubject().getSession().getId());
        resultMap.put("loginTime", SecurityUtils.getSubject().getSession().getStartTimestamp());
        log.info("{}登录成功", loginUser.getUsername());
        // 异步任务处理日志
        AsyncManager.me().execute(AsyncFactory.testTimerTask("用户登录成功"));
        return resultMap;
    }

    /**
     * 退出之前登陆的账号,只允许一个账号登录一次
     *
     * @param username
     */
    private void verifyUserSession(String username) {
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        if (sessions.isEmpty()) return;
        for (Session session : sessions) {
            Object attribute = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            SimplePrincipalCollection coll = (SimplePrincipalCollection) attribute;
            if (coll != null) {
                //清除该用户以前登录时保存的session
                Object principal = coll.getPrimaryPrincipal();
                if (!(principal instanceof SysUser))
                    continue;
                SysUser user = (SysUser) principal;
                if (username.equals(user.getUsername())) {
                    log.info("{}已登录，强制退出sessionId={}", username, session.getId());
                    sessionDAO.delete(session);
                    return;
                }
            }
        }
    }

    @Override
    public SysUser getUserByUsername(String username) {
        return userService.findByUsername(username);
    }

    @Override
    public UserInfo getInfo() {
        SysUser sysUser = userService.findByUsername(UserSecurityUtil.getUsername());
        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();
        if (UserSecurityUtil.isAdmin(null)) {
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

    @Override
    public R getCode() {
        // 生成随机字串
        String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
        // 唯一标识
        String uuid = IdUtil.simpleUUID();
        // 存入缓存
        String verifyKey = CustomConstant.CAPTCHA_CODE_KEY + uuid;
        if (config.isCaptchaCache()) {
            Cache<String, String> captchaCache = ehCacheManager.getCache(CustomConstant.CAPTCHA_CACHE_NAME);
            captchaCache.put(verifyKey, verifyCode);
            //captchaCache.put(verifyKey, verifyCode);
        }
        log.info("{}=={}", verifyKey, verifyCode);
        // 生成图片
        int w = 111, h = 36;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            VerifyCodeUtil.outputImage(w, h, stream, verifyCode);
            HashMap<String, Object> data = new HashMap<>();
            data.put("verify", config.isCaptchaCache());
            data.put("uuid", uuid);
            data.put("img", Base64.encode(stream.toByteArray()));
            return R.success().data(data);
        } catch (Exception e) {
            log.error("生成验证码失败，{}", e.getMessage());
            return R.error().msg("获取验证码失败");
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
