package com.noob.algorithm.dmsxl.leetcode.q055;

/**
 * 055 跳跃游戏
 */
public class Solution3 {

    /**
     * 范围覆盖
     */
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }

        // 定义覆盖范围
        int maxCover = 0;

        // 遍历每个节点：在覆盖范围内更新更大的覆盖范围
        for (int i = 0; i <= maxCover; i++) {
            // 在有效的覆盖范围内更新更大的覆盖范围
            maxCover = Math.max(maxCover, i + nums[i]);
            // 如果遍历过程中发现覆盖范围大于终点坐标，说明是可达终点的（此处在遍历过程中进行判断，不需要等到最后）
            if (maxCover >= nums.length - 1) {
                return true;
            }
        }

        // 其余情况
        return false;
    }
}
