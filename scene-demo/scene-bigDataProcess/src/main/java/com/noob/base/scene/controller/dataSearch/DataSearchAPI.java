package com.noob.base.scene.controller.dataSearch;

/**
 * 模拟数据检索API
 */
public class DataSearchAPI {

    private static void simulateProcess(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void doSearch(String batchNum) {
        long startTime = System.currentTimeMillis();
        simulateProcess(200); // 模拟延时200ms
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("模拟数据检索操作完成,本次操作编号{%s},耗时{%s}ms",batchNum, (endTime - startTime)));
    }

}
