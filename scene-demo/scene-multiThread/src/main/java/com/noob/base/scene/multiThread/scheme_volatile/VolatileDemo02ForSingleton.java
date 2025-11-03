package com.noob.base.scene.multiThread.scheme_volatile;

public class VolatileDemo02ForSingleton {
    public static void main(String[] args) {
        // 模拟构建1000个线程获取
        for (int i = 0; i < 1000; i++) {
            new Thread(
                    () -> {
                        System.out.println(DoubleCheckSingleton.getInstance());
                    }
            ).start();
        }
    }

}

/**
 * 基于双检锁模式的单例设计
 */
class DoubleCheckSingleton {

    private static volatile DoubleCheckSingleton instance;

    // 构造函数私有化
    private DoubleCheckSingleton() {
    }

    // 对外提供获取单例实例的方法
    public static DoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                    /**
                     * 上述语句实际上分拆为 3 个指令执行：
                     * 1.创建对象堆内存: DoubleCheckSingleton instance;
                     * 2.初始化对象实例: new DoubleCheckSingleton();
                     * 3.设置对象引用: instance = #3213
                     */
                }
                return instance;
            }
        }
        return instance;
    }
}
