package com.dc.project.open.controller;

import com.dc.common.vo.R;
import com.dc.common.vo.OpenUser;
import com.dc.project.open.service.IAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuangcy
 * @date 2020/11/14 16:26
 * @description 第三方认证控制层
 */
@Api(tags = "认证请求接口")
@RestController
@RequestMapping("/open/auth")
public class AuthController {
    @Autowired
    private IAuthService authService;

    /**
     * 客户登录
     *
     * @return
     */
    @ApiOperation(value = "登录接口")
    @PostMapping("/login")
    public R login(@RequestBody @Validated OpenUser user) {
        return R.success().data(authService.login(user));
    }

}
