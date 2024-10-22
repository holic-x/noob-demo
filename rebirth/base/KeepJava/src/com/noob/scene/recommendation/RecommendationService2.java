package com.noob.scene.recommendation;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 01.假设现在想做一个类似于抖音这样的APP，然后它有一个推荐页，就是抖音那个首页用户发一个请求，然后我就返回给他十来条视频。
 * 但其实这个推荐页背后有 4 个推荐系统独立的工作。所以我需要收到用户拉取推荐视频的这个请求之后去请求上游的 4 个推荐系统，然后对这 4 个推荐系统返回的结果进行一个去重，最后再返回给用户。
 * 我觉得串行的去请求 4 个推荐系统太浪费时间了，我想要并行的请求 4 个推荐系统，拿到他们返回的这个视频，列 4 个视频列表，然后再进行一个去重，最后返回给用户。那这个并行请求在去重的这个操作应该怎么写？
 */

class RecommendationTask implements Callable<List<String>> {

    private String systemId;

    RecommendationTask(String systemId) {
        this.systemId = systemId;
    }

    // 模拟获取视频列表
    @Override
    public List<String> call() throws Exception {
        System.out.println("模拟调用推荐系统获取数据" + systemId);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add("视频" + i);
        }
        // 此处模拟网络延迟
         Thread.sleep(1000+(long)Math.random()*200);
        return list;
    }
}

public class RecommendationService2 {

    /**
     * 构建思路：使用ExecutorCompletionService 其通过使用阻塞队列保存各个任务的返回结果，返回是无序的）**结果返回无序，谁先执行完就先入队
     */
    public List<String> handle() throws ExecutionException, InterruptedException {

        // 创建自定义线程池
        ExecutorService es = Executors.newFixedThreadPool(4);
        ExecutorCompletionService<List<String>> ecs = new ExecutorCompletionService<>(es);
        // 提交任务查询4个子系统
        ecs.submit(new RecommendationTask("1"));
        ecs.submit(new RecommendationTask("2"));
        ecs.submit(new RecommendationTask("3"));
        ecs.submit(new RecommendationTask("4"));
        // 执行结束关闭线程池回收资源
        es.shutdown();

        // 收集结果
        List<List<String>> res = new ArrayList<>();
        for(int i=0;i<4;i++){
            FutureTask<List<String>> futureTask = (FutureTask<List<String>>) ecs.take();
            List<String> list = futureTask.get();
            res.add(list);
        }

        // 对结果进行过滤（借助Stream进行过滤）
        Set<String> uniqueVideoList = res.stream().flatMap(Collection::stream).collect(Collectors.toSet());

        // 转换成列表并响应（例如此处推荐前10个元素）
        return new ArrayList<>(uniqueVideoList).subList(0,Math.min(10,uniqueVideoList.size()));
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RecommendationService2 recommendationService = new RecommendationService2();
        List<String> res = recommendationService.handle();
        System.out.println(res);
    }

}
