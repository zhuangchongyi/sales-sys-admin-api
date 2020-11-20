package com.dc.project.open.service.impl;

import com.dc.framework.realm.CustomUserToken;
import com.dc.framework.realm.LoginType;
import com.dc.common.vo.OpenUser;
import com.dc.project.open.service.IAuthService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuangcy
 * @date 2020/11/16 9:33
 * @description 客户认证Service实现类
 */
@Service
public class AuthServiceImpl implements IAuthService {

    @Override
    public Map<String, Object> login(OpenUser user) {
        CustomUserToken token = new CustomUserToken(user.getUsername(), user.getPassword(), LoginType.LOGIN_TYPE_WX);
        SecurityUtils.getSubject().login(token);
        HashMap<String, Object> result = new HashMap<>();
        result.put("token", SecurityUtils.getSubject().getSession().getId());
        return result;
    }
}
