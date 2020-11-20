package com.dc.project.open.service;

import com.dc.common.vo.OpenUser;

import java.util.Map;

/**
 * @author zhuangcy
 * @date 2020/11/16 9:33
 * @description 客户认证Service
 */
public interface IAuthService {
    Map<String, Object> login(OpenUser user);

}
