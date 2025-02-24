package com.noob.algorithm.solution_archive.dmsxl.other;

/**
 * 数组篇：07 区间和
 * 给定数组和指定要计算的区间，返回区间和
 */
public class Solution1 {

    // 方式1：根据区间计算累加和
    public int getIntervalSum1(int[] nums, int start, int end) {
        int sum = 0;
        // 根据区间索引计算累加和
        for (int i = start; i <= end; i++) {
            sum += nums[i];
        }
        // 返回区间累加和
        return sum;
    }

    // 方式2：前缀和（定义数组dp[] 指定位置上存储元素的前缀和）
    public int getIntervalSum2(int[] nums, int start, int end) {
        int len = nums.length;
        // 定义dp数组存储前缀和(此处的前缀和包括当前元素的累加和)
        int[] dp = new int[len];
        dp[0] = nums[0]; // 初始化
        for (int i = 1; i < len; i++) {
            dp[i] = dp[i - 1] + nums[i];
        }
        // 返回指定区间的前缀和
        if (start == 0) {
            return dp[end];
        } else {
            return dp[end] - dp[start - 1]; // 需单独处理start=0的情况避免数组越界
        }
    }

    public static void main(String[] args) {
        Solution1 s = new Solution1();
        int res1 = s.getIntervalSum1(new int[]{1, 2, 3, 4, 5}, 1, 2);
        System.out.println(res1);

        int res2 = s.getIntervalSum2(new int[]{1, 2, 3, 4, 5}, 0, 1);
        System.out.println(res2);
    }

}
