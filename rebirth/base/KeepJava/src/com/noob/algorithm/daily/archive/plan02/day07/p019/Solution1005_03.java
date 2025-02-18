package com.noob.algorithm.daily.archive.plan02.day07.p019;

import java.util.Arrays;

/**
 * 🟢 1005 K次取反后最大化的数组和 - https://leetcode.cn/problems/maximize-sum-of-array-after-k-negations/description/
 */
public class Solution1005_03 {

    /**
     * 思路分析：选择某个位置进行取反，需执行k次取反操作，可以多次选择同一个下标i，基于此操作返回数据可能的最大和
     * 贪心思路：如果数组元素中存在负数，则优先尽可能将负数转化为正数
     * - 优先转化最小的负数，如果数组中所有的负数转化后k还有剩余，则选择一个最小的正数进行一正一负抵消操作处理（剩余k为偶数直接抵消，剩余k为奇数则最小正数取反）
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        // ① 对数组元素进行排序
        Arrays.sort(nums);
        // ② 处理k次取反
        int sum = 0;
        int curMin = Integer.MAX_VALUE; // 维护最小值
        for (int i = 0; i < nums.length; i++) {
            // 如果为负数且k有剩余则执行取反
            if (nums[i] < 0 && k > 0) {
                nums[i] *= -1;
                k--;
            } else {
                // break; // 所有负数处理完成或者k不足了，则跳出处理
            }
            // 维护目前数组的最小值
            curMin = Math.min(curMin, nums[i]);
            // 遍历过程累加和
            sum += nums[i];
        }
        // ③ 校验剩余k
        if (k > 0) {
            if (k % 2 == 0) {
                return sum; // 剩余k为偶数，无论选择什么元素都是一正一负抵消，因此返回结果即为上述操作处理结果
            } else {
                // 选择目前数组的最小数取反
                return sum - 2 * curMin;
            }
        }
        // 返回结果
        return sum;
    }

}
