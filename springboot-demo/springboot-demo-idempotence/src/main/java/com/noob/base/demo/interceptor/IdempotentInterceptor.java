package com.noob.base.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Component
public class IdempotentInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Idempotent idempotent = handlerMethod.getMethodAnnotation(Idempotent.class);
            if (idempotent != null) {
                String key = idempotent.key();
                String value = request.getHeader(key); // 从请求头中获取唯一标识
                if (value == null || value.isEmpty()) {
                    throw new RuntimeException("幂等性标识不能为空");
                }
                if (redisTemplate.opsForValue().get(key + ":" + value) != null) {
                    throw new RuntimeException("重复请求");
                }
                redisTemplate.opsForValue().set(key + ":" + value, "1", idempotent.expireTime(), TimeUnit.SECONDS);
            }
        }
        return true;
    }
}