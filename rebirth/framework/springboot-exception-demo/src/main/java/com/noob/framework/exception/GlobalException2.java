package com.noob.framework.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 自定义异常
@ControllerAdvice
public class GlobalException2 {

    // 限定对何种异常进行处理，以及返回的格式
    @ExceptionHandler(value = ArithmeticException.class)
    public ModelAndView handler1(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("发生异常的处理器：" + handler + "具体异常信息：" + ex.getMessage());
        // 1.构建一个ModelAndView，用于最终返回
        ModelAndView modelAndView = new ModelAndView();

        // 2.构建一个错误页面（需加入一个视图解析器）
        modelAndView.setViewName("error/error");

        // 返回结果（必须返回一个ModelAndView对象，否则SpringMVC在处理的时候会报错）
        return modelAndView;
    }

    /**
     * @ExceptionHandler:限定对何种异常进行处理
     * @ResponseBody:处理返回的格式（SpringMVC会响应一个json格式信息）
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String handler2(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("发生异常的处理器：" + handler + "具体异常信息：" + ex.getMessage());
        // 返回结果
        return "{\n" +
                "    \"code\":\"-1\",\n" +
                "    \"msg\":\"服务器出现异常，请联系管理员处理...\",\n" +
                "    \"data\":null\n" +
                "}";
    }
}
