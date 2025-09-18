package com.noob.base.runtimeFailures.oom;

/**
 * 不同线程中处理OOM的行为差异
 */
public class OOMDiffThreadDemo2 {

    // 场景1：子线程处理OOM(JVM退出)
    private static void test_subThread_doHandleOOM() throws InterruptedException {
        // 子线程中发生OOM并及时处理
        Thread thread = new Thread(() -> {
            try {
                oomMethod();
            } catch (OutOfMemoryError e) {
                System.out.println("Handled OOM in thread. JVM will not exit.");
            }
        });
        thread.start();

        // 模拟后续处理操作，验证JVM的运行情况
        Thread.sleep(3000);
        System.out.println("test_subThread_doHandleOOM test finished");
    }

    // 场景2：子线程不处理OOM，且没有其他非守护线程运行(JVM退出)
    private static void test_subThread_unHandleOOM_withoutOtherRunThread() throws InterruptedException {
        // 子线程中发生OOM不处理
        Thread thread = new Thread(() -> {
            oomMethod();
        });
        thread.start();

        // 模拟后续处理操作，验证JVM的运行情况
        System.out.println("test_subThread_unHandleOOM_withoutOtherRunThread test finished");
//        Thread.sleep(3000);
    }

    // 场景3：子线程不处理OOM，且存在其他非守护线程运行(JVM不退出)
    private static void test_subThread_unHandleOOM_withOtherRunThread() throws InterruptedException {

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

        // 模拟后续处理操作，验证JVM的运行情况
        Thread.sleep(3000);
        System.out.println("test_subThread_unHandleOOM_withOtherRunThread test finished");
    }

    // 场景4：主线程处理OOM(JVM不退出)
    private static void test_mainThread_doHandleOOM() throws InterruptedException {
        // 主线程中发生OOM并及时处理
        try {
            oomMethod();
        } catch (OutOfMemoryError e) {
            System.out.println("Handled OOM in main. JVM will not exit.");
        }

        // 模拟后续处理操作，验证JVM的运行情况
        Thread.sleep(3000);
        System.out.println("test_mainThread_doHandleOOM test finished");
    }

    // 场景5：主线程不处理OOM(JVM退出)
    private static void test_mainThread_unHandleOOM() throws InterruptedException {
        // 主线程不处理OOM
        oomMethod();


        // 模拟后续处理操作，验证JVM的运行情况
        Thread.sleep(3000);
        System.out.println("test_mainThread_unHandleOOM test finished");
    }

    public static void main(String[] args) throws InterruptedException {
//        test_subThread_doHandleOOM();
//        test_subThread_unHandleOOM_withoutOtherRunThread();
        test_subThread_unHandleOOM_withOtherRunThread();
//        test_subThread_doHandleOOM();
//        test_mainThread_unHandleOOM();
    }

    // oom 触发模拟方法
    public static void oomMethod() {
        int[] array = new int[Integer.MAX_VALUE]; // 试图分配过大的数组，以触发OOM
    }
}