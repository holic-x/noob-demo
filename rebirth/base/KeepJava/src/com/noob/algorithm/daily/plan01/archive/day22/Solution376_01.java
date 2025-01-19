package com.noob.algorithm.daily.plan01.archive.day22;

/**
 * 🟡 376 摆动序列 - https://leetcode.cn/problems/wiggle-subsequence/description/
 */
public class Solution376_01 {

    public int wiggleMaxLength(int[] nums) {
        int len = nums.length;
        if (len == 0 || len == 1) {
            return len;
        }
        if (len == 2 && nums[0] != nums[1]) {
            return 2;
        }

        // 校验[x,y,z]关系，判断峰、谷的趋势变化
        int preDiff = nums[1] - nums[0]; // 记录上一个序列的趋势（上升、下降）
        int cnt = (preDiff == 0 ? 1 : 2); // 根据preDiff确认最长子序列的长度（如果是平坡则只有1个元素，如果是上升或下降则有两个元素）
        // 遍历数组，校验趋势变化关系
        for (int i = 2; i < len; i++) {
            int curDiff = nums[i] - nums[i - 1];
            if ((preDiff >= 0 && curDiff < 0) || (preDiff <= 0 && curDiff > 0)) {
                cnt++; // 趋势方向发生变化，则序列长度+1
                preDiff = curDiff; // 更新趋势状态
            }
        }

        // 返回结果值
        return cnt;
    }


}
