package com.noob.base.scene.controller.dataSearch;

/**
 * 数据检索
 */
public class DataSearchDemo01 {

    /**
     * ① 传统方式：串行调用处理
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            DataSearchAPI.doSearch(String.format("业务编号-%d", i));
        }
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("本次处理总耗时{%s}ms", (endTime - startTime)));
    }

}
