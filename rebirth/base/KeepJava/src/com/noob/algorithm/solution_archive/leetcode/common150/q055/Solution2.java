package com.noob.algorithm.solution_archive.leetcode.common150.q055;

/**
 * 55 跳跃游戏
 */
public class Solution2 {
    // 思路：动态规划
    public boolean canJump(int[] nums) {
        // 1.dp[i] 表示到达`i`位置时能到达的最远距离
        int[] dp = new int[nums.length];

        // 2.确认dp公式 dp[i] = max{dp[i-1],i+nums[i]} 即max{第i-1个节点能到达的最远位置 , 已走距离+当前可支持跳跃距离}

        // 3.初始化
        dp[0] = nums[0];

        // 4.确定循环
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] < i) { // 比较上一个覆盖范围可否到达当前节点
                return false;
            }
            dp[i] = Math.max(dp[i - 1], i + nums[i]);
        }

        // 5.遍历dp数据，判断dp对应的值是否可以支撑走到当前节点(即前一个范围能否覆盖到当前节点)
        // 此处可以在循环中就进行判断，如果走不到当前节点，则可以直接返回false
        return true;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 1, 4};
        System.out.println(new Solution2().canJump(nums));
    }
}
