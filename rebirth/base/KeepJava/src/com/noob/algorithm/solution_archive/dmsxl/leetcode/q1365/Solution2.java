package com.noob.algorithm.solution_archive.dmsxl.leetcode.q1365;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 🟢 1365 有多少小于当前数字的数字
 */
public class Solution2 {

    // 排序法
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int len = nums.length; // 定义数组长度
        int[] res = new int[nums.length]; // 定义统计结果数组

        // 对数组元素进行排序，找到第一个小于它的元素(数组元素可能重复，此处不适合用map处理，可以借助二维数组构建数组元素与原有索引坐标的关系)
        int[][] numMap = new int[len][2]; // [{ 元素值,对应原有坐标 }]
        for (int i = 0; i < len; i++) {
            numMap[i][0] = nums[i];
            numMap[i][1] = i;
        }

        // 对二维数组进行排序（从小到大）,则此时索引数字对应为前面有多少个比它小的数字
        Arrays.sort(numMap, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0]; // 根据元素值大小"从小到大"进行排序
            }
        });

        /**
         * 遍历每个元素，找到第1个比它小的元素(因为此处对数组进行了排序，所以当出现不连续的情况则需出现了比它小的数)
         * 数组本身有序，如果元素不重复的情况下，则索引值即为比当前元素小的数字个数
         * 但是如果元素出现重复，针对重复的情况则需要限定其与前面出现的保持一致
         */
        int prev = -1; // 记录第一个出现的数字比它小的数字个数（如果是连续出现则不变）
        for (int x = 0; x < numMap.length; x++) {
            // 如果初始化状态下或者是出现不连续的情况，则记录这个prev
            if (prev == -1 || numMap[x - 1][0] != numMap[x][0]) {
                prev = x; // 索引即为相应统计
            }
            // 处理对应索引位置的结果（根据原来的索引位置封装结果）
            res[numMap[x][1]] = prev;
        }

        // 返回处理结果
        return res;
    }

}
