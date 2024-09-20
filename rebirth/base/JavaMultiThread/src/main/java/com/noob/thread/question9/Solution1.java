package com.noob.thread.question9;

/**
 * 问题：确保t1、t2、t3线程顺序执行（t1执行完后执行t2，然后执行t3）
 * 思路1：只使用join方法：让主线程处于等待WAITING状态，只有调用join的线程不再活跃（执行完成）主线程才会继续往下
 */
public class Solution1 {

    public static void main(String[] args) throws InterruptedException {

        // 创建3个任务(线程)
        Thread t1 = new Thread(new Task("A"));
        Thread t2 = new Thread(new Task("B"));
        Thread t3 = new Thread(new Task("C"));

        // 启动线程t1，调用join方法等待线程t1执行完成
        t1.start();
        t1.join();
        // 继续启动线程t2、调用join方法等待线程t2执行完成
        t2.start();
        t2.join();
        // 继续启动线程t3、调用join方法等待线程t3执行完成
        t3.start();
        t3.join();

        // 所有线程执行完成，主线程继续往下
        System.out.println("所有任务执行完成");
    }

}

/**
 * 定义Task任务线程
 */
class Task implements Runnable {

    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        // 模拟任务执行沉睡1s
        try {
            System.out.println("任务" + taskName + "执行....");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
