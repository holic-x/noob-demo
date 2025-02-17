package com.noob.algorithm.daily.plan02.day07.p020;

/**
 * 🟡 376 摆动序列 - https://leetcode.cn/problems/wiggle-subsequence/description/
 */
public class Solution376_01 {

    /**
     * 思路分析：x、y、z，根据xy的情况寻找yz的趋势，获取摆动序列的最长子序列长度
     * preDiff = y-x,curDiff = z-y,根据preDiff与curDiff的趋势情况来判断是否要加入当前这个元素以构成更长的摆动序列
     */
    public int wiggleMaxLength(int[] nums) {
        // 特例判断
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return nums[0] == nums[1] ? 1 : 2;
        }

        // n > 2 的情况讨论
        int preDiff = nums[1] - nums[0]; // 初始化如果两个元素相同则选择1个加入摆动序列，否则选择两个加入摆动序列
        int cnt = (nums[0] == nums[1] ? 1 : 2); // preDiff==0?1:2
        for (int i = 2; i < n; i++) {
            int curDiff = nums[i] - nums[i - 1];
            // 校验preDiff与curDiff的关系(出现峰谷切换点)
            if ((preDiff <= 0 && curDiff > 0) || (preDiff >= 0 && curDiff < 0)) {
                cnt++;
                preDiff = curDiff; // 更新差值(可以理解为更新序列趋势)
            }
        }
        // 返回结果
        return cnt;
    }
}
