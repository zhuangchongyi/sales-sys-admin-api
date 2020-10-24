package com.dc.project.common.service;

import com.dc.common.vo.LoginUser;
import com.dc.common.vo.UserInfo;
import com.dc.project.system.entity.SysUser;

import java.util.Map;

public interface ILoginService {

    Map login(LoginUser loginUser);

    SysUser getUserByUsername(String username);

    UserInfo getInfo();

}
