package com.noob.algorithm.daily.plan03.hot100_daily.day07.p020;

/**
 * 🟡 376 摆动序列 - https://leetcode.cn/problems/wiggle-subsequence/description/
 */
public class Solution376_01 {

    /**
     * 思路分析：摆动序列，可以剔除某些元素达到摆动的效果，获取摆动序列的最长子序列
     * 仅有一个元素或者两个不等元素的序列也视作摆动序列，差值正负交替出现
     * 峰谷峰
     * 谷峰谷
     */
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        // ① 特例讨论：n≤2
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1; // 只有一个元素的子序列也视作摆动序列
        }
        if (n == 2) {
            return nums[0] == nums[1] ? 1 : 2; // 根据元素是否相同决定摆动序列的最大长度
        }

        // ② 趋势转折校验，判断摆动序列
        int preDiff = nums[1] - nums[0]; // 定义上一趋势
        int cnt = nums[0] == nums[1] ? 1 : 2; // 初始化
        for (int i = 2; i < n; i++) {
            // 校验当前趋势
            int curDiff = nums[i] - nums[i - 1];
            if ((preDiff < 0 && curDiff > 0) || (preDiff > 0 && curDiff < 0) || (preDiff == 0 && curDiff != 0)) {
                // 出现趋势转折或者平坡转折，则说明可以将当前序列纳入摆动序列
                cnt++;
                preDiff = curDiff; // 更新上一趋势
            }
        }
        // 返回结果
        return cnt;
    }
}
