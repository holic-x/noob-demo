package com.noob.algorithm.daily.design._1singleton;

/**
 * 单例模式：懒汉式（县城按原优化版本）
 */
public class Singleton04 {

    private static Singleton04 instance;

    private Singleton04() {
    }

    // synchronized 锁方法、锁操作（控制锁粒度）
    public static Singleton04 getInstance() throws InterruptedException {
        // 双检索模式
        if (instance == null) { // 过滤出第一批遇到instance为null的对象
            synchronized (Singleton04.class) {
                if (instance == null) { // 此处可确保先进入该代码块的线程优先执行创建，后面进入到该代码块的线程会发现instance已经被创建了就不会重复创建
                    // 模拟处理耗时
                    Thread.sleep(10);
                    instance = new Singleton04();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println(Singleton04.getInstance());
        }
    }
}
