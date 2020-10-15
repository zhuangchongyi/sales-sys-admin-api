package com.dc.project.common.service;

import com.dc.common.vo.LoginUser;
import com.dc.project.system.entity.SysUser;

import java.util.Map;

public interface ILoginService {

    Map login(LoginUser loginUser);

    SysUser getUserByUsername(String username);

    Map<String, Object> getInfo();

}
