package com.noob.algorithm.daily.plan03.hot100_daily.day08.p026;

/**
 * 🟡 416 分割等和子集 - https://leetcode.cn/problems/partition-equal-subset-sum/description/
 */
public class Solution416_01 {

    /**
     * 思路分析：
     * 确认target = sum/2
     * 转化为0-1背包问题：从n个物品中选择是否要放入背包，判断是否恰好可以装满背包
     */
    public boolean canPartition(int[] nums) {

        int m = nums.length;

        // 获取目标和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 奇偶判断
        if (sum % 2 == 1) {
            return false; // 不可能构成
        }
        // 如果sum为偶数，进一步处理
        int target = sum / 2;

        // 基于0-1背包思路处理问题
        // 1.dp 定义：dp[i][j] 表示从[0,i）个物品中选择放入容量为j的背包所能获得的最大价值
        int[][] dp = new int[m][target + 1];
        /**
         * 2.dp 递推：对于每个位置而言，基于当前选定物品可以选择要么放入、要么不放入，限制条件在于其容量是否可支撑
         * ① 可以放(j>=weight[i])，可自由选择放或者不放（max{a,b}）
         *  a.放入：dp[i][j] = dp[i-1][j-weight[i]] + value[i]
         *  b.不放入：dp[i][j] = dp[i-1][j]
         *
         *  ② 不能放：只能不放 dp[i][j] = dp[i-1][j]
         */
        // 3.dp 初始化
        dp[0][0] = 0; // 背包容量为0不能放入任何物品
        for (int j = 1; j <= target; j++) {
            dp[0][j] = (j >= nums[0]) ? nums[0] : 0;
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = 0;
        }

        // 先背包后容量（基于行方向遍历还是列方向遍历的概念）
        for (int i = 1; i < m; i++) {
            for (int j = 1; j <= target; j++) {
                // 校验每个物品是否可以放入当前背包
                if (j >= nums[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // 恰好装满背包
        return dp[m - 1][target] == target;
    }
}
