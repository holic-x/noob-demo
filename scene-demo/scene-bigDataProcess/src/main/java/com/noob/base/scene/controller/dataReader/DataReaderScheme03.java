package com.noob.base.scene.controller.dataReader;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DataReaderScheme03 {

    // 大文件路径
    private static final String BIG_FILE_PATH = "E:\\test\\test-files\\DataReaderScheme\\bigFile.txt";

    // 内存缓冲区大小
    private static final int BUFFER_SIZE = 8 * 1024 * 1024; // 8MB

    // 临时小文件存放目录
    private static final String TEMP_DIR = "E:\\test\\test-files\\DataReaderScheme\\temp_dir\\";

    // 小文件数量 (假设大文件1G，每个小文件100MB，则约10个) => 针对存储结果分片
    private static final int NUM_PARTITIONS = 10;

    /**
     * 处理每个子文件内容
     */
    public static Map<String, Long> processSmallFiles() throws Exception {
        File dir = new File(TEMP_DIR);
        File[] partFiles = dir.listFiles(f -> f.getName().startsWith("chunk-"));

        Map<String, Long> finalResult = new HashMap<>();

        for (File partFile : partFiles) {
            // 为每个小文件创建一个临时Map，用完即弃
            Map<String, Long> tempMap = new HashMap<>();

            BufferedReader chunkReader = new BufferedReader(new FileReader(partFile));
            String key;
            while ((key = chunkReader.readLine()) != null) {
                if (key.isEmpty()) continue;
                tempMap.put(key, tempMap.getOrDefault(key, 0L) + 1);
            }

            chunkReader.close();
            // partFile.delete(); // 可选：删除临时文件

            // 合并单文件处理结果
            finalResult.putAll(tempMap);
        }

        return finalResult;
    }

    /**
     * ③ 预分片 + 分片处理
     */
    public static void process() throws Exception {
        // 1. 初始化：创建所有小文件的写入流
        Map<Integer, BufferedWriter> writers = new HashMap<>();
        for (int i = 0; i < NUM_PARTITIONS; i++) {
            File tempFile = new File(TEMP_DIR + "chunk-" + i + ".txt");
            tempFile.getParentFile().mkdirs();
            writers.put(i, new BufferedWriter(new FileWriter(tempFile)));
        }

        // 2.预分片(全量扫描与路由)
        BufferedReader reader = new BufferedReader(new FileReader(BIG_FILE_PATH), BUFFER_SIZE);
        String line;
        Map<String, String> chunkLocalMap = new HashMap<>();
        long linesProcessed = 0;
        while ((line = reader.readLine()) != null) {
            String key = line.trim();
            if (key.isEmpty()) continue;

            // 直接路由，不进行分块内聚合
            int partition = Math.abs(key.hashCode() % NUM_PARTITIONS);
            BufferedWriter writer = writers.get(partition);
            // 每个key都写入一次
            writer.write(key + "\n"); // 简化处理：一个key一行

        }
        // 读取完毕关闭流
        reader.close();

        // finally 处理：关闭所有写入流 完成“预分片”
        for (BufferedWriter writer : writers.values()) {
            writer.close();
        }

        // 逐个统计（第二阶段I/O）
        Map<String, Long> finalResult = processSmallFiles(); // 此处可以合并后输出，也可单个单个文件输出处理
        finalResult.forEach((key, val) -> {
            System.out.println(String.format("key:%s,val:%d", key, val));
        });

    }

    public static void main(String[] args) throws Exception {
        process();
    }

}
