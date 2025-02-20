package com.noob.algorithm.daily.archive.plan02.hot100.day10.p030;

/**
 * 🟡 718 最长重复子数组（连续）
 */
public class Solution718_01 {
    /**
     * 思路分析：动态规划
     */
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length + 1, n = nums2.length + 1;
        // 1.dp 定义：dp[i][j] 表示[0,i-1]的nums1、[0,j-1]的nums2 的最长公共子数组长度
        int[][] dp = new int[m][n];

        /**
         * 2.dp 递推（根据当前比较元素位置的元素是否相等分情况讨论）
         * n1[i-1]==n2[j-1]:可构成连续 => dp[i][j] = dp[i-1][j-1] + 1
         * n1[i-1]!=n2[j-1]:无法构成连续 => dp[i][j] = 0
         */

        // 3.dp 初始化（首行首列）
        // 首行初始化
        for (int j = 0; j < n; j++) {
            dp[0][j] = 0;
        }
        // 首列初始化
        for (int i = 0; i < m; i++) {
            dp[i][0] = 0;
        }

        // 4.dp 构建
        int maxSubLen = 0;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxSubLen = Math.max(maxSubLen, dp[i][j]);
                } else {
                    dp[i][j] = 0; // 初始化默认值为0
                }
            }
        }

        // 返回结果
        return maxSubLen;
    }
}
