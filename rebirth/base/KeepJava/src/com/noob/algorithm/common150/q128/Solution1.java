package com.noob.algorithm.common150.q128;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 128 最长连续序列
 */
public class Solution1 {

    /**
     * 排序、去重、遍历寻找最长前缀
     */
    public int longestConsecutive(int[] nums) {
        // 定义最大序列长度
        int max = Integer.MIN_VALUE;

        // 对数组元素进行去重、排序
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            if (list.contains(num)) {
                continue;
            }
            list.add(num);
        }

        // 对处理后的列表进行特殊情况讨论
        if (list.size() <= 1) {
            return list.size();
        }

        /**
         * 遍历处理好的元素，寻找最长的连续序列
         * 1.连续序列相邻两个值之间间隔为1
         * 2.连续序列可以从中间任意节点开始
         * - 集合中可能存在多个连续序列，每个连续序列断开的点是下一个连续序列开始的点
         * - 元素本身就是一个连续序列，因此curLen从1开始计数
         */
        int curLen = 1; // 记录以当前指定元素为起点的连续序列长度
        // 计算以每个元素为起点能得到的最大连续序列，记录max
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i + 1) - list.get(i) == 1) {
                curLen++;
                max = Math.max(max, curLen);
            } else {
                curLen = 1; // 连续序列断掉，需重新计数
            }
        }

        // 返回结果
        return max == Integer.MIN_VALUE ? 1 : max; // 元素本身是一个序列
    }
}
