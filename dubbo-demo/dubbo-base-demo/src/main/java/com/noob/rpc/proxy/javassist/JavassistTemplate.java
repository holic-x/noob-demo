package com.noob.rpc.proxy.javassist;

public class JavassistTemplate {
    private String prop = "hello world";

    public void setProp(String var1) {
        this.prop = var1;
    }

    public String getProp() {
        return this.prop;
    }

    public JavassistTemplate() {
        this.prop = "dididi";
    }

    public void execute() {
        System.out.println("execute():" + this.prop);
    }
}