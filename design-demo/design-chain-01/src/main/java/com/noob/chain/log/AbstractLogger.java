package com.noob.chain.log;

public abstract class AbstractLogger {

    // 日志记录等级设定
    protected int level;

    // 定义下一个节点
    protected AbstractLogger nextLogger;

    // 定义处理方法
    public void handler(int level, String message){
        if(this.level <= level){
            write(message);
        }
        // 如果下一节点存在则继续打印
        if(nextLogger != null){
            nextLogger.handler(level, message);
        }
    }

    // 定义公共方法（下一节点设定）
    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    // 定义抽象方法（打印日志）
    public abstract void write(String msg);

}
