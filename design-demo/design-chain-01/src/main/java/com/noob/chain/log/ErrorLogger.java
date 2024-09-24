package com.noob.chain.log;

/**
 * Error级别日志打印处理
 */
public class ErrorLogger extends AbstractLogger{

    public ErrorLogger(int level){
        this.level = level;
    }

    @Override
    public void write(String msg) {
        System.out.println("error log: " + msg);
    }
}
