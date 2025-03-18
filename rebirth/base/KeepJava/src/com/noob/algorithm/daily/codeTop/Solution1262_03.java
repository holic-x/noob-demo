package com.noob.algorithm.daily.codeTop;


/**
 * 🟡 1262 可被3整除的最大和 - https://leetcode.cn/problems/greatest-sum-divisible-by-three/description/
 */
public class Solution1262_03 {

    /**
     * 提供一个整数数组 nums， 找出并返回能被三整除的元素 最大和
     * 基于动态规划思路
     */
    public int maxSumDivThree(int[] nums) {
        // 1.dp 定义：dp[i] 表示%3后余数为i的元素最大和
        int[] dp = new int[3];
        dp[1] = Integer.MIN_VALUE; // 初始化为负无穷，表示不可达
        dp[2] = Integer.MIN_VALUE; // 初始化为负无穷，表示不可达

        /**
         * 2.dp 递推
         * 对于每一个元素，更新dp（根据是否选择该元素分情况讨论）
         * - 不选择该元素：继承上一状态nextDp[i] = dp[i]
         * - 选择该元素：nextDp[num%3 + i] = max{dp[num%3 + i],dp[i] + nums[i]}
         * 上述nextDp只是临时存储的数组，每个元素遍历完成后需要更新dp
         */

        for (int num : nums) {
            int remainder = num % 3; // 当前元素%3的余数
            int[] nextDp = dp.clone(); // 定义nextDp临时存储更新后的状态(初始化需要复制当前数组)
            // 更新每个状态i（余数为0，1，2）
            for (int i = 0; i < 3; i++) {
                // 此处不需要区分不选择（动规中已经做了隐式处理）、选择两种情况
                int newRemainder = (remainder + i) % 3; // 计算加入该元素后构成数%3得到的余数
                nextDp[newRemainder] = Math.max(nextDp[newRemainder], dp[i] + num);
            }
            // 更新dp（用nextDp直接覆盖）
            dp = nextDp;
        }

        // 返回结果(返回模3余数为0的最大和)
        return dp[0];
    }

}
