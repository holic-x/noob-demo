package com.noob.thread.base;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/3/25
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class ThreadPriortyDemo extends Thread{
    public ThreadPriortyDemo(String name)
    {
        super(name);
    }
    @Override
    public void run() {
        for(int i=0;i<50;i++)
        {
            if(i==20)
            {
                // 线程让步，让其他线程拥有获取cpu的机会
                Thread.yield();
            }
            System.out.println(this.getName()+"执行"+i);
        }
    }
    public static void main(String[] args) {
        /**
         * 分别创建两个线程，设置不同的优先级
         * 通过setPriority方法设置不同的优先级（从1-10）
         * 执行两个线程，查看分析运行结果
         */
        ThreadPriortyDemo tp1 = new ThreadPriortyDemo("高级线程...");
        ThreadPriortyDemo tp2 = new ThreadPriortyDemo("低级线程...");
        tp1.setPriority(2);
        tp2.setPriority(1);
        /**
         * 	tp1.setPriority(MAX_PRIORITY);
         *  tp2.setPriority(MIN_PRIORITY);
         *  NORM_PRIORITY
         */
        tp1.start();
        tp2.start();
        /**
         * 由测试结果分析，虽然线程的优先级不同，但最终先执行结束的线程始终不确定，
         * 有可能是低级线程先执行结束，也有可能是高级线程先执行结束，但可以看到的是
         * 优先级较高的高级线程拥有比较大的几率抢占到cpu，因此优先级较高的线程先执行
         * 结束的可能性较大，但并不是一定.
         */
    }
}