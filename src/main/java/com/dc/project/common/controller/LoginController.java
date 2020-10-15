package com.dc.project.common.controller;

import com.dc.common.vo.LoginUser;
import com.dc.common.vo.R;
import com.dc.project.common.service.ILoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zhuangchongyi
 * @Description 用户认证控制层
 * @Date 2020/8/28 14:30
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private ILoginService loginService;

    @PostMapping("/login")
    public R login(@RequestBody LoginUser loginUser) {
        return R.success().msg("登录成功").data(loginService.login(loginUser));
    }

    @GetMapping("/logout")
    public R logout() {
        log.info("登出，销毁session=" + SecurityUtils.getSubject().getSession().getId().toString());
        SecurityUtils.getSubject().logout();
        return R.success().msg("退出成功");
    }


    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/getInfo")
    public R getInfo() {
        return R.success().data(loginService.getInfo());
    }

}
