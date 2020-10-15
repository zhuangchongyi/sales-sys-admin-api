package com.dc.framework.filter;

import com.alibaba.fastjson.JSON;
import com.dc.common.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Author zhuangchongyi
 * @Description 自定义的 Shiro 登录认证过滤器，继承FormAuthenticationFilter类拦截权限不足的请求，并返回JSON数据
 * @Date 2020/9/8 15:45
 */
@Slf4j
public class CustomAuthenticationFilter extends FormAuthenticationFilter {

    public CustomAuthenticationFilter() {
        super();
    }

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest req = WebUtils.toHttp(request);
            log.info(String.format("请求方式：%s, 请求路径：%s", req.getMethod(), req.getServletPath()));
            if (req.getMethod().toUpperCase().equals("OPTIONS")) {
                log.info("如果请求的方法是OPTIONS，总是返回true");
                return true;
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        log.info("shiroFilter拦截");
        HttpServletResponse res = WebUtils.toHttp(response);
        res.setHeader("Access-Control-Allow-Origin", "true");
        res.setContentType("application/json; charset=utf-8");
        res.setStatus(HttpServletResponse.SC_OK);
        PrintWriter writer = res.getWriter();
        writer.write(JSON.toJSONString(R.error().code(R.UNAUTHORIZED).msg("请求失败，未认证授权")));
        writer.close();
        //return false 拦截， true 放行
        return false;
    }

}

