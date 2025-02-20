package com.noob.algorithm.plan_archive.plan02.hot100.day08.p026;


/**
 * 🟡 494 目标和 - https://leetcode.cn/problems/target-sum/description/
 */
public class Solution494_01 {
    /**
     * 思路分析：给定要给数组，可以在元素前加入符号+/-然后串联起来得到表达式，返回通过上述方法构造的运算结果为target的不同表达式数目
     * 动态规划思路：0-1 背包问题
     * left-right=target、left+right=sum =》 left = (sum+target)/2
     */
    public int findTargetSumWays(int[] nums, int target) {

        // 计算元素累加和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int left = (target + sum) / 2;

        // 特例判断
        // ① 如果target+sum为奇数则没有方案
        if ((target + sum) % 2 == 1) return 0;

        // ② 如果target的绝对值大于sum，则基于现有的元素无法构成和为target
        if (Math.abs(target) > sum) return 0;


        // 动态规划求解
        // 1.dp[i][j] 表示使用[0,i]的物品能够凑满容量为j的背包共有多少种方法
        int m = nums.length, n = left + 1;
        int[][] dp = new int[m][n];

        /**
         * 2.dp递推：根据当前背包容量是否可以放下物品分情况讨论
         * - 放不下物品i：继承上一状态 dp[i][j] = dp[i-1][j]
         * - 可以放下物品i：选择放或者不放，得到两种情况下的方案数之和 dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i]]
         */

        // 3.dp 初始化
        // 首行初始化：dp[0][j] 表示放入物品0恰好可以凑满容量j有多少种方法（只有j==nums[0]的情况下才能凑满）
        for (int j = 0; j < n; j++) {
            dp[0][j] = (j == nums[0] ? 1 : 0);
        }
        // 首列初始化：dp[i][0] 表示放入物品i恰好可以凑满容量0有多少种方法（只有nums[i]==0才满足,但需讨论放、不放的情况）
        int zeroNum = 0; // 统计0的个数
        for (int i = 0; i < m; i++) {
            if (nums[i] == 0) {
                zeroNum++;
            }
            // 根据目前0的个数来决定方案数
            dp[i][0] = (int) Math.pow(2, zeroNum);
        }

        // 4.dp 构建
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (j >= nums[i]) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // 返回结果
        return dp[m - 1][left];

    }
}