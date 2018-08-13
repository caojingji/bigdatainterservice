package com.founder.interservice.config.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LogFilter implements Filter {
    /**
     * 过滤器初始化方法
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("过滤器初始化...............");
    }

    /**
     * 过滤器过滤方法
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException{
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        System.out.println(httpServletRequest.getRequestURL()); //http://localhost:8080/iphoneTrackForSjhm
        System.out.println(httpServletRequest.getMethod()); //GET
        System.out.println(httpServletRequest.getRequestURI()); //   /iphoneTrackForSjhm
        System.out.println(httpServletRequest.getRemoteAddr());
        System.out.println(httpServletRequest.getParameter("yhCate")); //客户端端口
        filterChain.doFilter(httpServletRequest,servletResponse);
    }

    /**
     * 过滤器销毁方法
     */
    @Override
    public void destroy() {
        System.out.println("过滤器销毁.............");
    }
}
