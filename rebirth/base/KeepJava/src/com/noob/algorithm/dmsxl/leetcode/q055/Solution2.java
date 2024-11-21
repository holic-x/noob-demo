package com.noob.algorithm.dmsxl.leetcode.q055;

/**
 * 055 跳跃游戏
 */
public class Solution2 {

    // 贪心误区：此处是跳跃概念，并不是每到一个点都能够"加油" todo  逻辑错误
    public boolean canJump(int[] nums) {
        // 特例判断
        if (nums == null || (nums != null && nums.length < 2)) {
            return true;
        }

        int maxStep = 0; // 定义当前位置可到达的最远位置
        int preChooseTarget = 0; // 记录当前上一步选择的步数可到达的目标点（此处直接记录目标点，如果记录步数还得关注起点）

        // 遍历每个节点：加油，并判断当前加油可否到达下一个节点
        for (int i = 0; i < nums.length; i++) {
            // 必须是到达上一步指定的目标点才需要进行操作，否则跳过
            if (preChooseTarget == i) {
                // 加油：更新当前可到达的最远位置（如果到达目标点才可以加油）
                maxStep += nums[i];

                // 更新下一个目标点
                preChooseTarget = i + nums[i]; // 计算下一个目标位置(当前位置+指定跳跃步数)
            }

            // 如果当前可到达的最远位置无法走下一步，则更加不可能往后走
            if (maxStep < preChooseTarget ) {
                return false;
            }


            // 如果当前可到达的最远位置超过总里程，则可到达终点
            if (maxStep > nums.length) {
                return true;
            }
        }

        // 整个遍历完成，说明可以到达终点
        return true;
    }
}
