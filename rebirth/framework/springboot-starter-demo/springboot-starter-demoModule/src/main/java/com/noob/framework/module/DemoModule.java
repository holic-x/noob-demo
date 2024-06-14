package com.noob.framework.module;

import org.springframework.util.StringUtils;

/**
 * 自定义DemoModule
 */
public class DemoModule {

    private String version;

    private String name;

    public String exeModuleMethod() {
        return "Demo module, version: " + version + ", name: " + name;
    }

    public DemoModule() {
    }

    public DemoModule(String name,String version) {
        // 校验参数信息
        if(StringUtils.isEmpty(name)){
            throw new IllegalArgumentException("name is empty");
        }
        if(StringUtils.isEmpty(version)){
            throw new IllegalArgumentException("version is empty");
        }
        this.version = version;
        this.name = name;
    }

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
