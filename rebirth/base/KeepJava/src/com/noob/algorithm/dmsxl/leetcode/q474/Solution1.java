package com.noob.algorithm.dmsxl.leetcode.q474;

/**
 * 474-一和零
 */
public class Solution1 {

    /**
     * 动态规划：基于01背包的一维数组思路构建
     *
     * @param strs 物品（重量分别为0、1的个数，价值为字符串长度）
     * @param m    最多0的个数（0背包容量）
     * @param n    最多1的个数（1背包容量）
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        // 1.dp定义（dp[i][j]表示最多有i个0、j个1的最大子集长度）
        int[][] dp = new int[m + 1][n + 1];

        /**
         * 2.递推公式
         * dp[i][j] = max{dp[i][j],dp[i-zeroNum][j-oneNum] + 1}
         */

        // 3.dp 初始化：初始化为0，后续递推更新
        dp[0][0] = 0;

        // 4.构建dp
        for (String str : strs) { // 外层遍历物品
            // 分别统计当前字符串str中0、1的个数
            int zeroNum = 0, oneNum = 0;
            for (char ch : str.toCharArray()) {
                if (ch == '0') {
                    zeroNum++;
                } else if (ch == '1') {
                    oneNum++;
                }
            }

            // 内层遍历背包(背包逆序)
            for (int i = m; i >= zeroNum; i--) {
                for (int j = n; j >= oneNum; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
                }
            }
        }

        // 返回结果
        return dp[m][n];

    }
}
