package com.noob.base.listener;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 监听ContextClosedEvent事件，处理未完成的请求
 */
@Component
public class ShutdownHandler {

    @EventListener
    public void handleContextClosed(ContextClosedEvent event) {
        // 在这里处理未完成的请求
        System.out.println("应用正在关闭，处理未完成的请求...");
        // 例如：等待所有线程池任务完成
        // 或者：关闭数据库连接、释放资源等
    }
}