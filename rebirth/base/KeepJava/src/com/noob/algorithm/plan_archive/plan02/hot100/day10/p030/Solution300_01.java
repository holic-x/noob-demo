package com.noob.algorithm.plan_archive.plan02.hot100.day10.p030;

import java.util.Arrays;

/**
 * 🟡 300 最长递增子序列（不连续）
 */
public class Solution300_01 {

    /**
     * 最长递增子序列
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // 1.dp 定义：dp[i] 表示以i位置元素结尾的最长递增子序列的长度
        int[] dp = new int[n];

        /**
         * 2.dp 递推
         * 校验每个元素，看元素是否可以拼接在前面的序列中构成最长子序列
         */

        // 3.dp 初始化(对于每一个数组元素，其本身就是一个最长子序列)
        Arrays.fill(dp, 1);

        // 4.dp 构建
        int maxSubLen = 1; // 元素本身也是一个子序列，此处初始化为1
        for (int i = 1; i < n; i++) { // 外层确定终点（即dp填充的位置）
            // 内层从头开始寻找，看当前元素可以拼接在前面的哪个序列末尾构成一个更长的子序列
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1); // 在内层循环过程中不断更新，寻找更大的子序列长度
                }
            }
            // 一层遍历完成，确定了dp[i],更新最大子序列值
            maxSubLen = Math.max(maxSubLen, dp[i]);
        }

        // 返回结果
        return maxSubLen;
    }

}
