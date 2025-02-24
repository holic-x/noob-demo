package com.noob.algorithm.solution_archive.dmsxl.leetcode.q494;

/**
 * 494 目标和
 */
public class Solution4 {

    // 动态规划（二维数组）
    public int findTargetSumWays(int[] nums, int target) {
        // 前置：动态规划思路转化（求解和为left组合个数）
        int m = nums.length; // 物品数量
        int sum = 0;
        for (int i = 0; i < m; i++) {
            sum += nums[i];
        }
        int left = (sum + target) / 2;

        // 特例情况判断：sum + target 的和为奇数，则找不到2个left的组合
        if ((sum + target) % 2 == 1) {
            return 0; // 这种情况方案数为0
        }
        // 特例情况判断：如果target的绝对值大于sum，则现有这些元素组合不可能构成target
        if (Math.abs(target) > sum) {
            return 0; // 这种情况方案数为0
        }

        // 动态规划处理
        // 1.dp定义：dp[i][j] 表示背包容量为j 装入[0,i]的物品的 装满的方案组合数
        int[][] dp = new int[m][left + 1];

        /**
         * 2.递推公式
         * j<nums[i] 装不下物品i，则dp[i][j]继承上一状态：dp[i][j] = dp[i-1][j]
         * j>=nums[i] 可以装下物品i，选择装或者不装：
         *  - 不装物品i：dp[i][j] = dp[i-1][j]
         *  - 装物品i：dp[i][j] = dp[i-1]][j-nums[i]] (先空出可以装物品i的容量)
         *  - 这种情况下的组合总数为：dp[i][j] = dp[i-1][j] +dp[i-1]][j-nums[i]]
         */

        // 3.dp初始化（首行、首列初始化）
        // 对于首行初始化，只有当容量j==nums[0]的情况下才能装满（方案数为1），其他情况要么装不满、要么装不下
        for (int j = 0; j <= left; j++) {
            dp[0][j] = (j == nums[0]) ? 1 : 0;
        }
        // 对于首列初始化，如果nums均不为0则容量j==0只有1种方案就是不装，但是如果nums中存在元素为0的情况则要根据0的个数计算方案数（2^t^,t为0的个数）
        int zeroNum = 0; // 累计0的个数
        for (int i = 0; i < m; i++) {
            if (nums[i] == 0) {
                zeroNum++;
            }
            // 根据当前0的个数来决定方案数
            dp[i][0] = (int) Math.pow(2, zeroNum);
        }

        // 4.构建dp（先物品后背包）
        for (int i = 1; i < m; i++) {
            for (int j = 1; j <= left; j++) {
                if (j < nums[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i]];
                }
            }
        }

        // 返回结果
        return dp[m - 1][left];
    }
}
