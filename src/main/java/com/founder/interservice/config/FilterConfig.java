package com.founder.interservice.config;

import com.founder.interservice.config.filter.LogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注册、管理过滤器
 */
@Configuration
public class FilterConfig {
    /**
     * 注册过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegist(){
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new LogFilter()); //设置过滤器
        frBean.setOrder(Integer.MAX_VALUE); //配置过滤器的优先级  数字越大 优先级越高
        frBean.addUrlPatterns("/*"); //设置过滤路径
        return frBean;
    }
}
