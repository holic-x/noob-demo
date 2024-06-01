package com.noob.multiThread;


// 结合单例模式中的【双检锁】
class Singleton{

    // 定义静态、私有的自己的对象
//    private static Singleton instance = null;
    // 引入volatile关键字避免重排序影响
    private static volatile Singleton instance = null;

    // 定义额外的变量
    private String descr;

    // 构造函数私有化
    private Singleton(){}

    // 对外提供公共方法获取实例(懒汉式)
    public static Singleton getInstance(){
        if(instance == null){
            synchronized (Singleton.class){
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

/**
 * 重排序 demo
 */
public class ReorderDemo {
    public static void main(String[] args) {
        // 模拟多线程获取单例对象
        final int threadSize = 500;
        for(int i = 0; i < threadSize; i++){
            new Thread(new Runnable(){
                @Override
                public void run(){
                    String threadName = Thread.currentThread().getName();
                    Singleton instance = Singleton.getInstance();
                    System.out.println("线程 " + threadName + "\t => " + instance.hashCode());
                }
            }).start();
        }
    }
}
