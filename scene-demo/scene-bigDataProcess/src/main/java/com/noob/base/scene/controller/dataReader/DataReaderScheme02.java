package com.noob.base.scene.controller.dataReader;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DataReaderScheme02 {

    // 大文件路径
    private static final String BIG_FILE_PATH = "E:\\test\\test-files\\DataReaderScheme\\bigFile.txt";

    // 内存缓冲区大小
    private static final int BUFFER_SIZE = 8 * 1024 * 1024; // 8MB

    // 临时小文件存放目录
    private static final String TEMP_DIR = "E:\\test\\test-files\\DataReaderScheme\\temp_dir\\";

    // 小文件数量 (假设大文件1G，每个小文件100MB，则约10个) => 针对存储结果分片
    private static final int NUM_PARTITIONS = 10;

    // 分组内聚合批量刷盘
    private static void flushChunkToFiles(Map<String, Integer> chunkLocalMap, Map<Integer, BufferedWriter> writers) throws IOException {
        for (Map.Entry<String, Integer> entry : chunkLocalMap.entrySet()) {
            String key = entry.getKey();
            int count = entry.getValue();
            // 计算路由
            int partition = Math.abs(key.hashCode() % NUM_PARTITIONS);
            BufferedWriter writer = writers.get(partition);
            // 写入格式：key + 分隔符 + count
            writer.write(key + "\t" + count + "\n");
        }
    }

    /**
     * 处理每个子文件内容
     */
    public static Map<String, Long> processSmallFiles() throws Exception {
        File dir = new File(TEMP_DIR);
        File[] partFiles = dir.listFiles(f -> f.getName().startsWith("part-"));

        Map<String, Long> finalResult = new HashMap<>();

        for (File partFile : partFiles) {
            BufferedReader reader = new BufferedReader(new FileReader(partFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                String key = parts[0];
                long count = Long.parseLong(parts[1]);
                finalResult.put(key, finalResult.getOrDefault(key, 0L) + count);

            }
            reader.close();
            // partFile.delete(); // 可选：删除临时文件
        }

        return finalResult;
    }

    /**
     * ② 分块读取/处理 + 分块内聚合处理
     */
    public static void process() throws Exception {
        // 1. 初始化：创建所有小文件的写入流
        Map<Integer, BufferedWriter> writers = new HashMap<>();
        for (int i = 0; i < NUM_PARTITIONS; i++) {
            File tempFile = new File(TEMP_DIR + "part-" + i + ".txt");
            tempFile.getParentFile().mkdirs();
            writers.put(i, new BufferedWriter(new FileWriter(tempFile)));
        }

        // 2.分块读取/处理 + 分块内聚合
        BufferedReader reader = new BufferedReader(new FileReader(BIG_FILE_PATH), BUFFER_SIZE);
        String line;
        Map<String, Integer> chunkLocalMap = new HashMap<>();
        long linesProcessed = 0;
        while ((line = reader.readLine()) != null) {
            String key = line.trim();
            if (key.isEmpty()) continue;

            // 分块内聚合
            chunkLocalMap.put(key, chunkLocalMap.getOrDefault(key, 0) + 1);

            // 策略：每处理一定数量的行（或达到缓冲区大小）就执行进行一次批量写入（例如此处【每处理1000行写入一次】）
            if (++linesProcessed % 1000 == 0) {
                flushChunkToFiles(chunkLocalMap, writers);
                chunkLocalMap.clear(); // 清空临时Map，准备下一批
            }
        }
        // 读取完毕关闭流
        reader.close();

        // 处理剩余的最后一批数据（刷盘）
        if (!chunkLocalMap.isEmpty()) {
            flushChunkToFiles(chunkLocalMap, writers);
        }

        // finally 处理：关闭所有写入流
        for (BufferedWriter writer : writers.values()) {
            writer.close();
        }

        // 遍历处理每个小文件得到最终的结果
        Map<String, Long> finalResult = processSmallFiles(); // 此处可以合并后输出，也可单个单个文件输出处理
        finalResult.forEach((key, val) -> {
            System.out.println(String.format("key:%s,val:%d", key, val));
        });

    }

    public static void main(String[] args) throws Exception {
        process();
    }

}
