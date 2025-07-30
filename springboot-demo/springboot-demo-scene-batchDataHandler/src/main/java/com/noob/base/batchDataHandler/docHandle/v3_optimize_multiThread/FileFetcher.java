package com.noob.base.batchDataHandler.docHandle.v3_optimize_multiThread;

import com.noob.base.batchDataHandler.docHandle.entity.FileInfo;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 多线程方式处理文件
 */
public class FileFetcher {

    private final ExecutorService executor;
    private final Map<String, InputStream> results = new ConcurrentHashMap<>();
    private String baseDir;

    public FileFetcher(String baseDir, int threadCount) {
        this.baseDir = baseDir;
        this.executor = Executors.newFixedThreadPool(threadCount);
    }

    public Map<String, InputStream> fetchFiles(List<FileInfo> fileInfoList) {
        CompletableFuture.runAsync(() -> {
            fileInfoList.forEach(fileInfo -> {
                try {
                    InputStream stream = getFileStreamByFileInfo(fileInfo.getFileName());
                    // 处理文件流
                    results.put(fileInfo.getFileKey(), stream);
                } catch (Exception e) {
                    // 处理异常
                }
            });
        }, executor);

        // 关闭线程池
        executor.shutdown();
        try {
            executor.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return results;
    }

    @SneakyThrows
    private InputStream getFileStreamByFileInfo(String fileName) {

        // 获取文件流(根据文件名称定位)
        String filePath = baseDir + File.separator + fileName;
        System.out.println("模拟文件处理服务：当前处理图片：" + fileName);
        return new FileInputStream(filePath);

    }
}
