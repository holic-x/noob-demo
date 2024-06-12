package com.noob.framework.module;

/**
 * 自定义DemoModule
 */
public class DemoModule {

    private String version;

    private String name;

    public String exeModuleMethod(){
        return "Demo module, version: " + version + ", name: " + name;
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
