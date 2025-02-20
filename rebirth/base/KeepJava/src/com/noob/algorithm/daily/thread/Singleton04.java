package com.noob.algorithm.daily.thread;

/**
 * 12.java 实现单例模式
 * - 枚举
 */
public class Singleton04 {

    public static void main(String[] args) {
        Single single = Single.SINGLE;
        single.print();
    }

    // 构建枚举
    enum Single{
        SINGLE;
        private Single(){

        }

        public void print(){
            System.out.println("hello world");
        }
    }
}
