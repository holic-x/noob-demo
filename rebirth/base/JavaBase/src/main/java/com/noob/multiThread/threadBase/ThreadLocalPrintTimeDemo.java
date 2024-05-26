package com.noob.multiThread.threadBase;

import java.util.concurrent.TimeUnit;

// 通过ThreadLocal实现打印一段代码的运行时间
class PrintCodeRunTime{

    // 1.创建一个ThreadLocal对象
    private static final ThreadLocal<Long> TIME_THREADLOCAL_OBJECT = new ThreadLocal<>();

    // 2.启动计时
    public static final void begin(){
        // set方法的key默认是TIME_THREADLOCAL_OBJECT对象，只需要设置value值
        TIME_THREADLOCAL_OBJECT.set(System.currentTimeMillis());
    }

    // 3.结束计时
    public static final long end(){
        // get方法无需指定key,TIME_THREADLOCAL_OBJECT对象会作为key
        long duration = System.currentTimeMillis() - TIME_THREADLOCAL_OBJECT.get();
        // 返回运行时间
        return duration;
    }
}

public class ThreadLocalPrintTimeDemo {
    public static void main(String[] args) throws InterruptedException {
        // 测试自定义基于ThreadLocal的计时器
        PrintCodeRunTime.begin();
        TimeUnit.SECONDS.sleep(2);
        long duration = PrintCodeRunTime.end();
        System.out.println("系统运行时间：" + duration + "毫秒");
    }
}
