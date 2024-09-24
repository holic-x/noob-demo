package com.noob.chain.log;

/**
 * File级别日志打印处理
 */
public class FileLogger extends AbstractLogger{

    public FileLogger(int level){
        this.level = level;
    }

    @Override
    public void write(String msg) {
        System.out.println("file log: " + msg);
    }
}
