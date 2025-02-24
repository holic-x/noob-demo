package com.noob.algorithm.plan_archive.plan01.day23;

/**
 * 🟡 055 跳跃游戏 - https://leetcode.cn/problems/jump-game/
 */
public class Solution055_01 {

    public boolean canJump(int[] nums) {
        // 记录当前可覆盖的最大距离
        int maxCover = nums[0]; // Integer.MIN_VALUE
        for (int i = 1; i < nums.length; i++) {
            // 如果距离可覆盖，则继续前进并更新最大覆盖距离
            if (maxCover >= i) {
                maxCover = Math.max(maxCover, i + nums[i]);
            } else {
                return false; // 记录不可覆盖到当前位置，则说明不可达
            }
        }
        // 所有元素遍历完成，说明所有地点可达
        return true;
    }

}
