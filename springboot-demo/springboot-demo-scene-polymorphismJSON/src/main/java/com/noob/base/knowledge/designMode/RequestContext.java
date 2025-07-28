package com.noob.base.knowledge.designMode;

import com.sun.security.auth.UserPrincipal;
import lombok.Data;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义请求上下文
 */
@Data
public class RequestContext {
    // 基础请求信息
    private final Map<String, String> params;
    private final String requestId;
    private final long requestTime;

    // 认证信息
    private UserPrincipal user;

    // 系统上下文
    private String clientIp;
    private String userAgent;

    // 业务扩展属性
    private Map<String, Object> attributes;

    // 构造方法
    public RequestContext(Map<String, String> params) {
        this.params = Collections.unmodifiableMap(new HashMap<>(params));
        this.requestId = UUID.randomUUID().toString();
        this.requestTime = System.currentTimeMillis();
        this.attributes = new ConcurrentHashMap<>();
    }

    // 关键方法
    public String getParam(String key) {
        return params.get(key);
    }

    public String getParam(String key, String defaultValue) {
        return params.getOrDefault(key, defaultValue);
    }

    public int getIntParam(String key, int defaultValue) {
        try {
            return Integer.parseInt(params.get(key));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public void setAttribute(String key, Object value) {
        attributes.put(key, value);
    }

    public <T> T getAttribute(String key) {
        return (T) attributes.get(key);
    }

    // 其他getter/setter...
}