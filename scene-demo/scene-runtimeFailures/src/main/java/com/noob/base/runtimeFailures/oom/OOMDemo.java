package com.noob.base.runtimeFailures.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟oom场景
 */
public class OOMDemo {

    public static void main(String[] args) {
        // 创建一个列表用于保存对象，防止被GC回收
        List<byte[]> memoryList = new ArrayList<>();
        int count = 0;

        try {
            // 不断分配1MB大小的字节数组
            while (true) {
                byte[] buffer = new byte[1024 * 1024]; // 1MB
                memoryList.add(buffer);
                count++;

                // 每分配100MB打印一次信息
                if (count % 100 == 0) {
                    System.out.println("已分配 " + count + " MB 内存");
                }
            }
        } catch (OutOfMemoryError e) {
            System.out.println("发生OutOfMemoryError! 总共分配了 " + count + " MB 内存");
            e.printStackTrace();
        }
    }


}
