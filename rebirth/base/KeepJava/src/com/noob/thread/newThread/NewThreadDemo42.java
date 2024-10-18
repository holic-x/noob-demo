package com.noob.thread.newThread;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * 创建线程的方式
 * 04-线程池（借助ExecutorService）
 */
public class NewThreadDemo42 {

    // ExecutorService+Executors构建线程池，执行Callable类型任务,自定义保存任务返回结果
    public static void test01() throws ExecutionException, InterruptedException {
        // 创建线程池(例如此处创建一个固定线程池大小的线程池，初始化值设定为10)
        ExecutorService es = Executors.newFixedThreadPool(10);
        // 定义集合接收处理结果(此处FutureTask可以根据泛型定义指定相应的类型，默认是Object)
        List<FutureTask> futureTaskList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            // 启动线程并执行
            FutureTask futureTask = new FutureTask(new NewTask(String.valueOf("task" + i)));
            es.submit(futureTask);
            // 添加处理结果
            futureTaskList.add(futureTask);
        }
        // 执行结束关闭线程池
        es.shutdown();
        // 处理返回结果
        for (int i = 0; i < futureTaskList.size(); i++) {
            FutureTask futureTask = futureTaskList.get(i);
            System.out.println("处理响应结果：" + futureTask.get());
        }
    }

    // ExecutorService+Executors构建线程池，执行Callable类型任务,使用ExecutorCompletionService保存返回结果（返回是无序的，谁先执行完成就先入队）
    public static void test02() throws InterruptedException, ExecutionException {
        // 创建线程池(例如此处创建一个固定线程池大小的线程池，初始化值设定为10)
        ExecutorService es = Executors.newFixedThreadPool(10);
        ExecutorCompletionService executorCompletionService = new ExecutorCompletionService(es);
        for (int i = 0; i < 100; i++) {
            executorCompletionService.submit(new NewTask(String.valueOf("task" + i)));
        }
        // 执行结束关闭线程池
        es.shutdown();
        // 处理返回结果
        for (int i = 0; i < 100; i++) {
            // 使用FutureTask接收返回结果(依次从executorCompletionService中取出结果)
            FutureTask futureTask = (FutureTask) executorCompletionService.take();
            System.out.println("处理响应结果：" + futureTask.get());
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        NewThreadDemo42.test01();
        NewThreadDemo42.test02();
    }
}

// 自定义任务
class NewTask implements Callable {

    // 定义任务相关参数
    public String taskNum;

    // 提供构造函数（此处用于初始化taskNum）
    public NewTask(String taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public Object call() throws Exception {
        System.out.println("NewTask run:" + Thread.currentThread().getName() + "执行任务" + taskNum);
        return taskNum;
    }
}
