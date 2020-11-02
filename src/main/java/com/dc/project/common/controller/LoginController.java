package com.dc.project.common.controller;

import com.dc.common.constant.CustomConstant;
import com.dc.common.utils.Base64;
import com.dc.common.utils.IdUtils;
import com.dc.common.utils.VerifyCodeUtil;
import com.dc.common.vo.LoginUser;
import com.dc.common.vo.R;
import com.dc.project.common.service.ILoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

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

    /**
     * 获取验证码
     *
     * @return
     */
    @GetMapping("getCode")
    public R getCode() {
        // 生成随机字串
        String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
        // 唯一标识
        String uuid = IdUtils.simpleUUID();
        String verifyKey = CustomConstant.CAPTCHA_CODE_KEY + uuid;
        // TODO<zhuangcy> 验证码存入缓存
        log.info("验证码:" + verifyCode);
        // 生成图片
        int w = 111, h = 36;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            VerifyCodeUtil.outputImage(w, h, stream, verifyCode);
            HashMap<String, Object> data = new HashMap<>();
            data.put("uuid", uuid);
            data.put("img", Base64.encode(stream.toByteArray()));
            return R.success().data(data);
        } catch (Exception e) {
            log.error(String.format("生成验证码失败，%s", e.getMessage()));
            return R.error().data(e.getMessage());
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
