package com.noob.algorithm.dmsxl.leetcode.q062;

/**
 * 062 不同路径
 */
public class Solution2 {

    /**
     * 动态规划
     */
    public int uniquePaths(int m, int n) {
        // 1.定义dp[i][j]:表示从(0,0)到(i,j)位置共有多少条路径
        int[][] dp = new int[m][n]; // 如果m、n要作为数组下标的话，此处长度要扩宽为[m+1][n+1]
        /**
         * 2.递推公式
         * dp[i,j] 只能从两个方向过来：(i-1,j)向下走1步；(i,j-1)向右走1步
         * 基于此构建递推公式为：dp[i][j]=dp[i-1][j] + dp[i][j-1]
         */
        // 3.dp 初始化((0,0)往同一行或者同一列方向行进，路径始终只有1条)
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        // 4.构建dp（从(1,1)节点开始）
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        // 返回结果（(0,0)->(m-1,n-1)共有多少条路径）
        return dp[m - 1][n - 1];
    }

}
