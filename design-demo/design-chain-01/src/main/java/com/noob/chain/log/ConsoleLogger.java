package com.noob.chain.log;

/**
 * 控制台打印处理
 */
public class ConsoleLogger extends AbstractLogger{

    public ConsoleLogger(int level){
        this.level = level;
    }

    @Override
    public void write(String msg) {
        System.out.println("console log: " + msg);
    }
}
