package com.noob.algorithm.solution_archive.dmsxl.leetcode.q055;

/**
 * 055 跳跃游戏
 */
public class Solution1 {
    // 贪心误区：此处是跳跃概念，并不是每到一个点都能够"加油" todo 代码存在逻辑错误
    public boolean canJump(int[] nums) {
        // 定义当前位置可到达的最远位置
        int maxStep = 0;

        // 遍历每个节点：加油，并判断当前加油可否到达下一个节点
        for (int i = 0; i < nums.length; i++) {
            // 加油：更新当前可到达的最远位置
            maxStep += nums[i];

            if (maxStep < i + 1) {
                // 如果当前可到达的最远位置无法到达下一个加油站，则更加不可能往后走
                return false;
            }

            // 提前终止（如果当前加油量超过总里程，则必然可以到达终点，提前剪枝）
            if (maxStep > nums.length) {
                return true;
            }
        }

        // 整个遍历完成，说明可以到达终点
        return true;
    }
}
