package com.noob.algorithm.daily.design._1singleton;

/**
 * 单例模式：懒汉式（县城按原优化版本）
 */
public class Singleton03 {

    private static Singleton03 instance;

    private Singleton03() {
    }

    // synchronized 锁方法、锁操作（控制锁粒度）
    public static Singleton03 getInstance() {
        // 条件锁模式
        synchronized (Singleton03.class) {
            if (instance == null) {
                instance = new Singleton03();
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Singleton03.getInstance());
        }
    }
}
