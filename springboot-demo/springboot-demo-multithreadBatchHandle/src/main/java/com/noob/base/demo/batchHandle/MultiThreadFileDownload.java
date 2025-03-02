package com.noob.base.demo.batchHandle;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ① 文件下载
 */
public class MultiThreadFileDownload {

    @SneakyThrows
    private static void downloadFile(String url) {
        // 模拟不同文件下载
        Thread.sleep(new Random().nextInt(3000));
        System.out.println("下载路径：" + url + "《=》线程【" + Thread.currentThread().getName() + "】处理完成");
    }

    public static void main(String[] args) {
        // 定义线程池（10个线程）
        ExecutorService executor = Executors.newFixedThreadPool(10);
        // 模拟100个文件的URL
        String[] fileUrls = new String[100];
        for (int i = 0; i < fileUrls.length; i++) {
            fileUrls[i] = "https://exam.com//file" + i + ".zip";
        }

        // 提交任务到线程池
        for (String url : fileUrls) {
            executor.submit(() -> {
                downloadFile(url);
            });
        }

        // 任务执行完成关闭线程池
        executor.shutdown();
    }
}
