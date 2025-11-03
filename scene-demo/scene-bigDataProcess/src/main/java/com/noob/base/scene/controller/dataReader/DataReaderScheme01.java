package com.noob.base.scene.controller.dataReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class DataReaderScheme01 {

    // 大文件路径
    private static final String BIG_FILE_PATH = "E:\\test\\test-files\\DataReaderScheme\\bigFile.txt";

    // 内存缓冲区大小
    private static final int BUFFER_SIZE = 8 * 1024 * 1024; // 8MB

    /**
     * ① 分块读取 + Map
     */
    public static void process() throws Exception {
        Map<Character, Integer> resultMap = new HashMap<>();
        // 分块读取大文件,统计字符重复出现次数
        BufferedReader reader = new BufferedReader(new FileReader(BIG_FILE_PATH), BUFFER_SIZE);
        String line;
        while ((line = reader.readLine()) != null) {
            // 读取当行数据并统计
            String lineStr = line.trim();
            for (Character key : lineStr.toCharArray()) {
                resultMap.put(key, resultMap.getOrDefault(key, 0) + 1);
            }
        }

        // 打印统计结果
        resultMap.forEach((key, val) -> {
            System.out.println(String.format("key:%s,val:%d", key, val));
        });
    }

    public static void main(String[] args) throws Exception {
        process();
    }

}
