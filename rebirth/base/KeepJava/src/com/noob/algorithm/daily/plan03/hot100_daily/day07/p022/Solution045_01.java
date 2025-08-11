package com.noob.algorithm.daily.plan03.hot100_daily.day07.p022;

/**
 * 🟡 045 - 跳跃游戏II - https://leetcode.cn/problems/jump-game-ii/description/
 */
public class Solution045_01 {

    /**
     * 思路分析：已知跳跃可达，返回到达终点的最小跳跃次数
     * - 每次跳跃选择可达范围内的最远跳跃距离的位置执行跳跃
     */
    public int jump(int[] nums) {

        int maxCover = nums[0]; // 当前最大可跳距离
        int step = 0; // 当前跳跃次数
        int jumpPointer = 0; // 上一次指定的跳跃位置

        for (int i = 0; i < nums.length - 1; i++) { // 限定在有效的跳跃距离范围内执行跳跃
            // 遍历过程中不断更新有效的覆盖范围
            maxCover = Math.max(maxCover, i + nums[i]);
            // 如果到达上一次指定的跳跃位置则执行跳跃操作并更新下一跳的目标位置（选择最远跳跃位置执行）
            if(i==jumpPointer){
                step++;
                jumpPointer = maxCover;
            }
        }
        // 返回结果
        return step;
    }
}
