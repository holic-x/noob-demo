package com.noob.algorithm.solution_archive.dmsxl.leetcode.q045;

public class Solution1 {
    /**
     * 以最少的次数跳到终点（nums用例可以支持跳到终点）
     */
    public int jump(int[] nums) {
        int maxCover = 0; // 最大覆盖范围(当前位置i可跳的最大位置)
        int jumpPointer = 0; // 上一跳指定的可跳的最大位置 preStepMaxTarget
        int cnt = 0; // 跳跃次数

        for (int i = 0; i < nums.length - 1; i++) {
            // 遍历过程中不断更新有效的覆盖范围
            maxCover = Math.max(maxCover, i + nums[i]);

            // 每次都选上一步可达到的最远的位置跳
            if (i == jumpPointer) {
                cnt++; // 跳
                jumpPointer = maxCover; // 当前可覆盖的最大范围作为下一个跳跃目标位置
            }
        }

        // 返回结果
        return cnt;
    }
}
