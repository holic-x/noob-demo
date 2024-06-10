package com.noob.framework.design.observer;

import org.springframework.context.ApplicationEvent;

// 定义事件，继承ApplicationEvent并写相应的构造方法
public class DemoEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;

    private String message;

    public DemoEvent(Object source,String message){
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
