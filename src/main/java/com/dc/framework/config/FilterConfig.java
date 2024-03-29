package com.dc.framework.config;

import com.dc.framework.filter.XssFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * Filter配置
 */
@Configuration
public class FilterConfig {
    @Value("${sms.xss.enabled}")
    private String enabled;

    @Value("${sms.xss.excludes}")
    private String excludes;

    @Value("${sms.xss.urlPatterns}")
    private String urlPatterns;

    @Bean
    public FilterRegistrationBean<Filter> xssFilterRegistration() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns(StringUtils.split(urlPatterns, ","));
        registration.setName("xssFilter");
        registration.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);
        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("excludes", excludes);
        initParameters.put("enabled", enabled);
        registration.setInitParameters(initParameters);
        return registration;
    }

//    @Bean
//    public FilterRegistrationBean<Filter> someFilterRegistration() {
//        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
//        registration.setFilter(new RepeatableFilter());
//        registration.addUrlPatterns("/*");
//        registration.setName("repeatableFilter");
//        registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
//        return registration;
//    }

}
