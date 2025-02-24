package com.noob.algorithm.solution_archive.dmsxl.leetcode.q1049;

/**
 * 1049 最后一块石头的重量II
 */
public class Solution2 {

    // 动态规划：一维数组版本
    public int lastStoneWeightII(int[] stones) {
        int m = stones.length;

        // 获取重量之和
        int sum = 0;
        for (int i = 0; i < m; i++) {
            sum += stones[i];
        }
        int bagSize = sum / 2;

        // 1.dp[]定义（dp[j] 表示容量为j的背包中物品的最大价值）
        int[] dp = new int[bagSize + 1];

        // 2.递推公式：dp[j] = Math.max(dp[j],dp[j-stones[i]]+stones[i])

        // 3.dp[]初始化（dp[0]为0，其余按照正常条件递推）

        // 4.dp[]构建
        for (int i = 0; i < m; i++) { // 外层物品
            for (int j = bagSize; j >= stones[i]; j--) { // 内层背包容量（逆序遍历）
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }

        // 返回结果
        return sum - 2 * dp[bagSize];
    }

}
