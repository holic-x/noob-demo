package com.noob.algorithm.daily.plan03.hot100_daily.day07.p019;

import java.util.Arrays;

/**
 * 🟢 1005 K次取反后最大化的数组和 - https://leetcode.cn/problems/maximize-sum-of-array-after-k-negations/description/
 */
public class Solution1005_01 {

    /**
     * 思路分析：
     * 数组存在负数，因此尽可能将负数都取正，且k有盈余的情况下通过一正一负来抵消（选择一个目前的最小正整数来做为处理参考）
     */
    public int largestSumAfterKNegations(int[] nums, int k) {

        int sum = 0;

        // 对数组元素进行排序，从小到大来进行负数取正操作
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            // 判断是否为负数且k是否有盈余
            if (nums[i] < 0 && k > 0) {
                nums[i] *= -1; // 负数取正
                k--; // k 消耗1次
            }
            /*
            if (k <= 0) {
                break; // 如果k消耗完成则可提前退出循环处理
            }
             */
            sum += nums[i]; // 计算处理后的累加和
        }

        // 校验k是否还有次数
        if (k <= 0) {
            return sum; // 如果k无剩余则返回结果（处理完成）
        }

        // 如果k有剩余则继续选择一个目前的最小正数来做抵消
        Arrays.sort(nums);
        int minNum = nums[0];
        // 校验k的奇偶性
        return (k % 2 == 0) ? sum : sum - 2 * minNum;

    }

}
