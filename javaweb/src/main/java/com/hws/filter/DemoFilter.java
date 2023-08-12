package com.hws.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {
    @Override // 初始化方法，只调用一次
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("inti 初始化方法执行");
    }

    @Override //拦截到请求之后调用，调用多次
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截到请求");

        //放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override //销毁方法，只调用一次
    public void destroy() {
        System.out.println("destroy 销毁方法执行");
    }
}
