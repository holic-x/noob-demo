package com.noob.design.singleton;

/**
 * 单例模式：懒汉式
 */
public class Singleton01Q {
    // 1.定义一个私有的自身的静态对象
    private static Singleton01Q singleton ;

    // 2.构造函数私有化
    private Singleton01Q(){
    }

    // 3.对外提供一个公共的方法用于操作该对象
    public static Singleton01Q getInstance() throws InterruptedException {
        // 懒汉式的核心在于只有在真正使用的时候才会去初始化对象
        if(singleton == null){
            // 为了体现并发效果此处延迟一段时间模拟业务操作
            Thread.sleep(1000);

            singleton = new Singleton01Q();
        }
        return singleton;
    }

    public static void main(String[] args) throws InterruptedException {
        // 测试并发场景下的单例获取情况
        for(int i=0;i<1000;i++){
            // 构建多个线程获取
            Thread t = new Thread(()->{
                try {
                    System.out.println(new Singleton01Q().getInstance());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            t.start();
        }
    }
}
