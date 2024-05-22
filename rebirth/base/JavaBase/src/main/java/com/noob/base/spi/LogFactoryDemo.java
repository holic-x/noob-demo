package com.noob.base.spi;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * LogFactory demo
 */
public class LogFactoryDemo {

    // 调试日志
    private final static Log log = LogFactory.getLog(LogFactoryDemo.class);

    public static void main(String[] args) {
        log.debug("debug 模式");
        log.error("一个错误");
        log.info("info日志输出");
    }
}
