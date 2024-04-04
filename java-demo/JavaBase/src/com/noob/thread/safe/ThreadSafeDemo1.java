package com.noob.thread.safe;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/3/26
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class ThreadSafeDemo1 {
    public int count = 0;
    /**
     * 1.创建公共类，提供自加方法，供三个线程访问
     */
    class Demo
    {
        /**
         * 三个线程访问同一个对象，要确保线程安全问题，因此该方法用
         * synchronized关键字加锁，从而使得该方法一次只能允许一个线程进入
         */
        public synchronized void add()
        {
            count++;
        }
    }
    /**
     * 分别创建三个线程用以访问Demo类中的add方法
     */
    Demo demo = new Demo();
    public void init()
    {
        // 线程1
        new Thread(new Runnable(){
            @Override
            public void run() {
                for(int i=0;i<10000;i++){
                    demo.add();
                }
            }
        }).start();

        // 线程2
        new Thread(new Runnable(){
            @Override
            public void run() {
                for(int i=0;i<10000;i++){
                    demo.add();
                }
            }
        }).start();

        // 线程3
        new Thread(new Runnable(){
            @Override
            public void run() {
                for(int i=0;i<10000;i++){
                    demo.add();
                }
            }
        }).start();

        while(Thread.activeCount()!=1)
        {
            /**
             * 阻塞,当上方分三个线程都执行结束之后再执行主线程,
             * 否则当执行还没有达到30000便执行主线程，提前结束操作
             * 则不会得到预期的30000（且每次执行的结果显示都不尽相同）
             */
            System.out.println("主线程阻塞");
        }
        System.out.println(count);
    }
    public static void main(String[] args) {
        // 测试
        ThreadSafeDemo1 tsd = new ThreadSafeDemo1();
        tsd.init();
    }
}