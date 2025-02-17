package com.noob.algorithm.daily.plan02.day07.p019;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 🟢 1005 K次取反后最大化的数组和 - https://leetcode.cn/problems/maximize-sum-of-array-after-k-negations/description/
 */
public class Solution1005_02 {

    /**
     * 思路分析：选择某个位置进行取反，需执行k次取反操作，可以多次选择同一个下标i，基于此操作返回数据可能的最大和
     * 贪心思路：如果数组元素中存在负数，则优先尽可能将负数转化为正数
     * - 优先转化最小的负数，如果数组中所有的负数转化后k还有剩余，则选择一个最小的正数进行一正一负抵消操作处理（剩余k为偶数直接抵消，剩余k为奇数则最小正数取反）
     * - 此处进一步优化排序，则可借助最小堆来进行处理
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        // ① 构建最小堆辅助处理
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        // 初始化堆
        for (int i = 0; i < nums.length; i++) {
            pq.offer(nums[i]);
        }

        // ② 遍历元素进行k次取反操作
        while (k-- > 0) {
            // 每次从堆中选择一个最小的数字进行取反（即最小堆的堆顶元素）
            int top = pq.poll();
            pq.offer(top*-1); // 取反并重新入堆
        }

        // ③ 弹出堆中元素并累加和
        int sum = 0;
        while (!pq.isEmpty()){
            sum += pq.poll();
        }

        // 返回结果
        return sum;
    }

}
