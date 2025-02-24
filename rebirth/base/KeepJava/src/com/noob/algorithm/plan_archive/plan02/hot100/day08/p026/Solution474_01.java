package com.noob.algorithm.plan_archive.plan02.hot100.day08.p026;

/**
 * 🟡 474 一和零 - https://leetcode.cn/problems/ones-and-zeroes/description/
 */
public class Solution474_01 {

    /**
     * 思路分析：动态规划 01背包问题
     * 给出字符串strs和 整数m、n，找出并返回strs的最大子集长度，子集中最多有m个0、n个1
     */
    public int findMaxForm(String[] strs, int m, int n) {
        /**
         * 1.dp定义：dp[i][j]表示最多有i个0、j个1的strs的最大子集的大小为dp[i][j]
         * 需注意此处并不是01背包的二维数组概念，而是一维数组的组合版本（因为此处两个维度都是背包容量维度）
         */
        int[][] dp = new int[m + 1][n + 1];

        /**
         * 2.dp 递推
         * dp[i][j]是基于上一个状态推导得出，假设当前遍历的字符串（物品）中有zNum个0、oNum个1
         * 则需要空出目前所需的字符个数才能放下该字符串
         * dp[i][j] = dp[i-zNum][j-oNum] + 1 （子集长度）
         * 要求得最大子集长度则为在遍历的过程中更新最大值 dp[i][j]=max{dp[i][j],dp[i-zeroNum][j-oneNum] + 1}
         */

        // 3.初始化
        dp[0][0] = 0; // 后续经由递推推导

        // 4.dp 构建(一维数组：先物品后背包、背包逆序)
        // 外层物品：str
        for (String str : strs) {
            // 统计strs中每个字符串中0、1的个数（在循环的过程中处理）
            int zNum = 0, oNum = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') {
                    zNum++;
                } else if (c == '1') {
                    oNum++;
                }
            }

            // 内层背包（背包逆序）
            for (int i = m; i >= zNum; i--) {
                for (int j = n; j >= oNum; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zNum][j - oNum] + 1);
                }
            }
        }
        // 返回结果
        return dp[m][n];
    }
}
