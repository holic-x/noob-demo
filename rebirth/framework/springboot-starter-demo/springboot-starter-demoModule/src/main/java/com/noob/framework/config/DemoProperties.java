package com.noob.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

// 设定配置前缀
@ConfigurationProperties(prefix = "custom.noob.demo")
public class DemoProperties {
    private String version;
    private String name;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}