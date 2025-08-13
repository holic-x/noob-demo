package com.noob.algorithm.daily.plan03.hot100_daily.day08.p026;


/**
 * 🟡 494 目标和 - https://leetcode.cn/problems/target-sum/description/
 */
public class Solution494_02 {
    /**
     * 思路分析：动态规划思路
     * 设P为正数子集，Q为负数子集，要满足目标设定则有sum(P)-sum(Q)=target,sum(P)+sum(Q)=sum(all)
     * =>sum(P)=(target+sum)/2 即转义为寻找nums中满足该条件的子集，即此时(target+sum)/2即为bagSize（背包容量目标）
     * =>问题转化：在nums中挑选元素满足元素和等于bagSize的集合，此处求的是方案总数
     */
    public int findTargetSumWays(int[] nums, int target) {
        // 循环遍历求nums
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 校验bagSize的有效性
        // 特例1：和为奇数无法构建（和为奇数，结合公式则说明sum(P)不是整数，因此这个场景无解）
        if ((sum + target) % 2 == 1) {
            return 0;
        }

        // 特例2：|target| > sum，因为sum已经是非负整数的和了，所有元素的绝对值不可能超过sum，因此如果目标target如果超过sum的话场景无解
        if (Math.abs(target) > sum) {
            return 0;
        }

        // 动态规划思路解决

        // 1.dp定义：dp[i][j] 表示从[0,i)中的元素中放置元素恰好可以凑满容量j的背包的最大方案数
        int bagSize = (sum + target) / 2;
        int[][] dp = new int[nums.length][bagSize + 1];
        int m = dp.length, n = dp[0].length; // 定义dp数组长度属性

        /**
         * 2.dp递推：对于每个元素，在基于可放或者不可放的前提下，可以选择放或者不放
         */

        // 3.初始化
        dp[0][0] = (nums[0] == 0) ? 2 : 0; // 对于容量为0的背包，如果nums[0]为0则存在2种方案(正负0)，如果nums[0]不为0则无可行方案

        // 首行初始化（放第0个物品）
        for (int j = 1; j < n; j++) {
            dp[0][j] = (j == nums[0]) ? 1 : 0; // 因为要恰好放满背包，所以必须是容量与元素相同的情况下才能放
        }
        // 首列初始化（背包容量为0）:对于容量为0的背包，只能放下nums[i]==0的元素，但此处方案个数涉及到组合的问题，因此是要根据nums元素中已经出现的0的个数来决定放置方案总数=》0的个数为x，则方案总数为2的x次方
        int zeroCnt = 0;
        for (int i = 0; i < m; i++) { // 初始化要从0开始，因为要计算已经出现0的个数，因此不能忽略nums[0]为0的场景
            if (nums[i] == 0) {
                zeroCnt++;
            }
            // 更新方案数
            dp[i][0] = (int) Math.pow(2, zeroCnt);
        }

        // 4.dp构建：01背包问题
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (j >= nums[i]) {
                    // 背包可放下当前物品：可以选择放或者不放，两种方案的总和
                    dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j];
                } else {
                    // 背包无法放下当前物品
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // 返回结果
        return dp[m - 1][bagSize];
    }

}