package com.noob.scene.recommendation;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * 01.假设现在想做一个类似于抖音这样的APP，然后它有一个推荐页，就是抖音那个首页用户发一个请求，然后我就返回给他十来条视频。
 * 但其实这个推荐页背后有 4 个推荐系统独立的工作。所以我需要收到用户拉取推荐视频的这个请求之后去请求上游的 4 个推荐系统，然后对这 4 个推荐系统返回的结果进行一个去重，最后再返回给用户。
 * 我觉得串行的去请求 4 个推荐系统太浪费时间了，我想要并行的请求 4 个推荐系统，拿到他们返回的这个视频，列 4 个视频列表，然后再进行一个去重，最后返回给用户。那这个并行请求在去重的这个操作应该怎么写？
 */
public class RecommendationService1 {

    // 模拟获取视频列表
    public List<String> getVideoList(String systemId) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add("视频" + i);
        }
        // 此处模拟网络延迟
        // Thread.sleep(100+(long)Math.random()*200);
        return list;
    }

    /**
     * 构建思路：使用CompletableFuture的思路
     */
    public List<String> handle() throws ExecutionException, InterruptedException {
        // 并行查询4个子系统
        CompletableFuture<List<String>> future1 = CompletableFuture.supplyAsync(() -> getVideoList("1"));
        CompletableFuture<List<String>> future2 = CompletableFuture.supplyAsync(() -> getVideoList("2"));
        CompletableFuture<List<String>> future3 = CompletableFuture.supplyAsync(() -> getVideoList("3"));
        CompletableFuture<List<String>> future4 = CompletableFuture.supplyAsync(() -> getVideoList("4"));

        /*
        // 等待所有Future完成，收集结果并进行过滤
        CompletableFuture.allOf(future1, future2, future3, future4).join();
        // 收集结果
        List<List<String>> res = Arrays.asList(future1.get(),future2.get(),future3.get(),future4.get());
         */

        // 等待所有Future完成，收集结果(使用thenApply收集结果并处理可能的异常)并进行过滤
        List<List<String>> res = CompletableFuture.allOf(future1, future2, future3, future4).thenApply(
                v -> {
                    try {
                        System.out.println("处理结果......");
                        return Arrays.asList(future1.get(),future2.get(),future3.get(),future4.get());
                    } catch (InterruptedException | ExecutionException e){
                        throw new RuntimeException(e);
                    }
                }
        ).join();

        // 对结果进行过滤（借助Stream进行过滤）
        Set<String> uniqueVideoList = res.stream().flatMap(Collection::stream).collect(Collectors.toSet());

        // 转换成列表并响应（例如此处推荐前10个元素）
        return new ArrayList<>(uniqueVideoList).subList(0,Math.min(10,uniqueVideoList.size()));
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RecommendationService1 recommendationService = new RecommendationService1();
        List<String> res = recommendationService.handle();
        System.out.println(res);
    }

}
