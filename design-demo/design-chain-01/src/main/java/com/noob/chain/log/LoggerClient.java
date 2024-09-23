package com.noob.chain.log;

/**
 * 日志打印客户端测试
 */
public class LoggerClient {
    // 1-INFO级别、2-DEBUG级别、3-ERROR级别
    private static final int LEVEL_INFO = 1;
    private static final int LEVEL_DEBUG = 2;
    private static final int LEVEL_ERROR = 3;


    private static AbstractLogger getLoggerChain(){
        AbstractLogger consoleLogger = new ConsoleLogger(LEVEL_INFO);
        AbstractLogger fileLogger = new FileLogger(LEVEL_DEBUG);
        AbstractLogger errorLogger = new ErrorLogger(LEVEL_ERROR);
        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);
        // 返回责任链节点（最高级别的节点：头节点）
        return errorLogger;
    }

    public static void main(String[] args) {
        // 获取责任链
        AbstractLogger loggerChain = getLoggerChain();
        // 执行
        loggerChain.handler(LEVEL_INFO,"this is an info level message");
        System.out.println("----------");
        loggerChain.handler(LEVEL_DEBUG,"this is an debug level message");
        System.out.println("----------");
        loggerChain.handler(LEVEL_ERROR,"this is an error level message");
    }
}
