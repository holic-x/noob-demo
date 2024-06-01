package com.noob.multiThread.threadBase;

public class IllegalThreadStateExceptionDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread.start(); // 启动线程
        thread.start(); // 不允许，会抛出IllegalThreadStateException
    }

}
