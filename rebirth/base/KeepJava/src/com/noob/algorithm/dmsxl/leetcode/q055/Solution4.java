package com.noob.algorithm.dmsxl.leetcode.q055;

/**
 * 055 跳跃游戏
 */
public class Solution4 {

    /**
     * 范围覆盖
     */
    public boolean canJump(int[] nums) {
        int maxCover = 0; // 定义当前最大可覆盖范围

        for(int i=0;i<nums.length;i++){
            // 判断当前最大覆盖范可否支持走到i位置
            if(maxCover<i){
                return false; // 无法支持
            }
            // 更新更大的覆盖范围
            maxCover = Math.max(maxCover,nums[i]+i); // 看跳到哪个位置可以走得更远就选择跳到那个位置并更新最大可覆盖范围
        }
        // 循环结束说明可以走到末尾
        return true;
    }
}
