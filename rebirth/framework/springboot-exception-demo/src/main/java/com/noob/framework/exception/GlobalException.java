package com.noob.framework.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 自定义异常
//@Component
public class GlobalException implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("发生异常的处理器：" + handler + "具体异常信息：" + ex.getMessage());
        // 1.构建一个ModelAndView，用于最终返回
        ModelAndView modelAndView = new ModelAndView();

        // 2.构建一个错误页面（需加入一个视图解析器）
        modelAndView.setViewName("error/error");

        // 3.返回一个错误的JSON格式文件信息
        response.setContentType("text/html;charset=UTF-8");
        try {
            response.getWriter().write("{\n" +
                    "    \"code\":\"-1\",\n" +
                    "    \"msg\":\"服务器出现异常，请联系管理员处理...\",\n" +
                    "    \"data\":null\n" +
                    "}");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 返回结果（必须返回一个ModelAndView对象，否则SpringMVC在处理的时候会报错）
        return modelAndView;
    }
}
