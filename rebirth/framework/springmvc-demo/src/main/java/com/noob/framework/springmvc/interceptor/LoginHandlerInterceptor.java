package com.noob.framework.springmvc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 登录校验，获取请求的URI
        String url=request.getRequestURI();
        if(!url.toLowerCase().contains("login")) {
            //非登录请求,获取session,判断是否有用户数据
            if(request.getSession().getAttribute("current_user")!=null) {
                // 传输登录用户信息
                request.setAttribute("username", request.getSession().getAttribute("current_user"));
                //已经登录,放行
                return true;
            }else {
                //没有登录则跳转到登录页面
                request.setAttribute("message", "您还没有登录，请先登录");
                request.getRequestDispatcher("/index/toLogin").forward(request, response);
            }
        }else {
            return true;//登录请求，放行
        }
        return false;//默认拦截
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
