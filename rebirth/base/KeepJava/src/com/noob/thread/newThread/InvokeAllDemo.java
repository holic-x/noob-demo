package com.noob.thread.newThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class InvokeAllDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new InvokeAllDemo().invokeAllTest();
    }

    /**
     * 方法三：ExecutorService的invokeAll方法
     * invokeAll方法入参为一组任务，返回一组Future，这两个集合是有相同结构的，
     * 即它是按照入参的任务集合中迭代器的顺序将所有的Future添加到返回的集合中，从而任务和Future在它们各自的集合中有着同样的顺序。
     * 当我们需要任务和结果的对应关系时，使用invokeAll方法来代替第一种方法
     */
    public void invokeAllTest() throws InterruptedException, ExecutionException {
        // 初始化线程池
        ExecutorService es = Executors.newFixedThreadPool(10);

        // invokeAll 接收的是一组任务，此处先初始化任务列表
        List<TestTask> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tasks.add(new TestTask(i));
        }

        // 调用invokeAll执行，并使用Future进行接收
        List<Future<Integer>> futures = es.invokeAll(tasks); // 此处需注意TestTask的定义，需指定泛型类型，否则编译转化cue红线

        //执行完成关闭线程池
        es.shutdown();

        // 遍历返回结果
        for (int i = 0; i < futures.size(); i++) {
            System.out.println("index:" + i + ",future:" + futures.get(i).get());
        }
    }

}

/**
 * 测试任务，返回任务的序号
 */
class TestTask implements Callable<Integer> {
    int index;

    public TestTask(int index) {
        this.index = index;
    }

    @Override
    public Integer call() throws Exception {
        return index;
    }
}

