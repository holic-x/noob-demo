package com.noob.algorithm.daily.plan03.hot100_daily.day07.p019;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 🟢 1005 K次取反后最大化的数组和 - https://leetcode.cn/problems/maximize-sum-of-array-after-k-negations/description/
 */
public class Solution1005_02 {

    /**
     * 思路分析：
     * 数组存在负数，因此尽可能将负数都取正，且k有盈余的情况下通过一正一负来抵消（选择一个目前的最小正整数来做为处理参考）
     * - 优化思路：这个过程中涉及到2次排序，因此可以考虑用一个数据结构来维持变化的序列（例如最小堆，来动态维护数组元素变化过程中的有序性）
     */
    public int largestSumAfterKNegations(int[] nums, int k) {

        // 排序
        Arrays.sort(nums);

        // 构建最小堆
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        // 对数组元素进行排序，从小到大来进行负数取正操作，构建完成的数据载入堆
        int sum = 0;
        for (int num : nums) {
            int curNum = num;
            // 负数处理
            if (num < 0 && k > 0) {
                curNum = -1 * curNum;
                k--;
            }
            // 载入堆元素，并统计累加和
            pq.offer(curNum);
            sum += curNum;
        }

        // 校验k是否还有次数
        if (k <= 0) {
            return sum; // 如果k无剩余则返回结果（处理完成）
        }

        // 如果k有剩余则继续选择一个目前的最小正数来做抵消(此时小顶堆的堆顶元素即为最小正整数)
        int minNum = pq.peek();
        // 校验k的奇偶性
        return (k % 2 == 0) ? sum : sum - 2 * minNum;

    }

}
