package com.noob.algorithm.leetcode.common150.q045;

/**
 * 45 跳跃游戏II
 */
public class Solution1 {

    /**
     * 贪心思路：每次都选择当前能跳的最大距离位置进行跳
     */
    public int jump(int[] nums) {

        int max = 0; // max记录当前i位置可跳的最大位置
        int point = 0; // point记录上一跳指定的可跳的最大位置
        int step = 0; // 记录步数

        for(int i=0;i<nums.length;i++){
            // 更新当前位置可跳的最大位置
            max = Math.max(max,i+nums[i]);
            // 如果走到上一跳指定的最大的位置，则跳
            if(point == i){
                step++; // 当前位置可跳
                point = max ;// 更新下一个可跳的最大位置
            }
        }

        // 返回步数
        return step;
    }

}
