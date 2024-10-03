package com.noob.algorithm.leetcode.q55;

/**
 * 55.跳跃游戏
 */
public class Solution {
    public boolean canJump(int[] nums) {
        int max = 0; // 此前可支撑的最大里程
        for(int i=0;i<nums.length;i++){
            // 判断当前的最大里程是否可以支持走到当前节点
            if(max<i){
                return false; // 无法走到当前节点
            }
            // 更新最大的覆盖范围（此处并不是简单的max+=nums[i]）
            max = Math.max(max, i + nums[i]);
        }
        return true;
    }
}
