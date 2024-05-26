package com.noob.multiThread.threadBase;

public class ThreadStartDemo {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println("线程111111");
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println("线程222222");
                }
            }
        }).start();
    }
}
