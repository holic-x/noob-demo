package com.noob.algorithm.dmsxl.leetcode.q674;

import java.util.Arrays;

public class Solution2 {
    public int findLengthOfLCIS(int[] nums) {
        int n = nums.length;
        // 特例判断
        if (n == 1) {
            return 1;
        }

        // 1.dp定义：dp[i]表示以i位置所在元素结尾的最长连续递增子序列的长度（连续）
        int[] dp = new int[n];

        /**
         * 2.dp推导
         * dp[i]=1(为自身的情况)
         * dp[i]= Math.max(dp[i], dp[i - 1] + 1);（判断是否可以接在前一个元素上，不能接则断开）
         */

        // 3.dp初始化
        Arrays.fill(dp, 1);

        // 4.dp构建
        int maxLen = 0;
        for (int i = 1; i < n; i++) {
            // 此处只需要判断可否接在前一个元素上
            if (nums[i] > nums[i - 1]) {
                dp[i] = Math.max(dp[i], dp[i - 1] + 1);
            }
            // 更新max
            maxLen = Math.max(maxLen, dp[i]);
        }

        // 返回结果
        return maxLen;
    }

}
