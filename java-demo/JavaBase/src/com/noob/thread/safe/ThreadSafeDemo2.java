package com.noob.thread.safe;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/3/26
 * @Copyright： 无所事事是薄弱意志的避难所
 */
class SellTicket implements Runnable {

    // 定义总票数
    private int tickets = 100;

    @Override
    public void run() {
        while (true) {
            if (tickets <= 0) {
                System.out.println("票已售罄");
            } else {
                // 模拟卖票
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 执行卖票操作
                tickets--;
                System.out.println(Thread.currentThread().getName() + "已出一票，目前剩余" + tickets + "张票");
            }
        }
    }
}


class SaftSellTicket implements Runnable {

    // 定义总票数
    private int tickets = 100;
    private Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            // 同步代码块:加锁
            synchronized (obj){
                if (tickets <= 0) {
                    System.out.println("票已售罄");
                } else {
                    // 模拟卖票
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 执行卖票操作
                    tickets--;
                    System.out.println(Thread.currentThread().getName() + "已出一票，目前剩余" + tickets + "张票");
                }
            }
        }
    }
}

public class ThreadSafeDemo2 {
    public static void main(String[] args) {
        // 创建SellTicket类的对象
        // SellTicket st = new SellTicket(); // 线程不安全
        SaftSellTicket st = new SaftSellTicket(); // 借助同步代码块方式构建，保证线程安全

        // 创建三个Thread类的对象，把SellTicket对象作为构造方法的参数，并给出对应的窗口名称
        Thread t1 = new Thread(st, "窗口1");
        Thread t2 = new Thread(st, "窗口2");
        Thread t3 = new Thread(st, "窗口3");

        // 启动线程
        t1.start();
        t2.start();
        t3.start();
    }
}
