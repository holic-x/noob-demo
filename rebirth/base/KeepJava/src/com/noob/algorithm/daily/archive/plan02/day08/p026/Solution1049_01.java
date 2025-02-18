package com.noob.algorithm.daily.plan02.day08.p026;

/**
 * 🟡 1049 - 最后一块石头的重量II - https://leetcode.cn/problems/last-stone-weight-ii/
 */
public class Solution1049_01 {

    /**
     * 思路分析：类似求解等和子集的概念，尽量让石头分成重量相同的两堆，相撞之后剩下的石头最小，以此化解成01背包问题
     * 背包容量为sum/2，最终返回结果为sum-2*dp[m-1][bagSize]
     */
    public int lastStoneWeightII(int[] stones) {
        // 计算数组和
        int sum = 0;
        for (int num : stones) {
            sum += num;
        }

        // 定义背包容量
        int bagSize = sum / 2;

        // 1.dp定义 dp[i][j] 表示容量为j的背包装入物品i可获得的最大物品价值
        int m = stones.length, n = bagSize + 1;
        int[][] dp = new int[m][n];

        /**
         * 2.dp 递推：根据是否可以放入物品选择放后者不放
         */

        // 3.dp 初始化
        // 首行初始化dp[0][j] 容量为j的背包放入物品0可获得的最大价值
        for (int j = 0; j < n; j++) {
            dp[0][j] = (j >= stones[0] ? stones[0] : 0);
        }
        // 首列初始化dp[i][j] 容量为0的背包放入物品i可获得的最大价值（物品价值均为正数，容量为0可以理解为放不下任何物品）
        for (int i = 0; i < m; i++) {
            dp[i][0] = 0;
        }

        // 4.dp 构建
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (j >= stones[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i]] + stones[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // 返回结果
        return sum - 2 * dp[m - 1][bagSize];
    }
}
