package com.dc.framework.filter;

import com.dc.common.utils.ObjectMapperUtil;
import com.dc.common.utils.http.ServletUtil;
import com.dc.common.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

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
            log.info("请求方式：{}, 请求路径：{}", req.getMethod(), req.getServletPath());
//            if (req.getMethod().equals(HttpMethod.OPTIONS)) {
//                log.info("如果请求的方法是OPTIONS，总是返回true");
//                return true;
//            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        log.info("shiro拦截，请求方式：{}, 请求路径：{}", WebUtils.toHttp(request).getMethod(), WebUtils.toHttp(request).getServletPath());
        ServletUtil.renderString(WebUtils.toHttp(response), ObjectMapperUtil.toJSON(R.error().code(R.UNAUTHORIZED).msg("请求失败，未认证授权")));
        //return false 拦截， true 放行
        return false;
    }

}

