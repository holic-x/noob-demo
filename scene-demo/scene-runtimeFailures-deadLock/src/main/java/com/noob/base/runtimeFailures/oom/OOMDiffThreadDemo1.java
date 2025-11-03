package com.noob.base.runtimeFailures.oom;

/**
 * 不同线程中处理OOM的行为差异
 */
public class OOMDiffThreadDemo1 {

    public static void main(String[] args) throws InterruptedException {
        // 子线程中发生OOM并及时处理
        Thread thread1 = new Thread(() -> {
            try {
                oomMethod();
            } catch (OutOfMemoryError e) {
                System.out.println("Handled OOM in thread. JVM will not exit.");
            }
        });

        // 子线程中发生OOM不处理
        Thread thread2 = new Thread(() -> {
            oomMethod();
        });

        thread1.start();
        Thread.sleep(3000);

        thread2.start();
        Thread.sleep(3000);
        // 主线程中发生OOM并且及时处理，JVM不会退出
        try {
            oomMethod();
        } catch (OutOfMemoryError e) {
            System.out.println("Handled OOM in main. JVM will continue.");
        }

        // 主线程中发生OOM而未处理,JVM会马上退出
        System.out.println("JVM退出前");
        oomMethod();
        System.out.println("JVM退出后");
    }

    // oom 触发模拟方法
    public static void oomMethod() {
        int[] array = new int[Integer.MAX_VALUE]; // 试图分配过大的数组，以触发OOM
    }
}