package com.noob.base.scene.multiThread.scheme_volatile;

public class VolatileDemo01ForReProduce {
    public static void main(String[] args) {
        Task task = new Task();

        // 构建2个线程分别处理状态更新和等待结果两个方法
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(2000); // 模拟处理耗时，确保让t2线程先进入循环
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            task.doWork();
        });
        Thread t2 = new Thread(() -> {
            task.waitForCompletion();
        });

        // 启动线程执行
        t1.start();
        t2.start();
    }

}

class Task {

    private boolean isFinished = false;

    private int counter = 0; // 新增变量(用于增加内部循环体的复杂度)

    public void doWork() {
        System.out.println("状态修改-start");
        isFinished = true; // 模拟状态修改
        System.out.println("状态修改-end");
    }

    public void waitForCompletion() {
        System.out.println("等待状态完成-start");
        while (!isFinished) {
            counter++; // 增加循环体的复杂度，避免JIT偷懒导致"可见性"问题复现不稳定
            // 等待任务完成
        }
        System.out.println("Task Completed....");
        System.out.println("等待状态完成-end");
    }
}