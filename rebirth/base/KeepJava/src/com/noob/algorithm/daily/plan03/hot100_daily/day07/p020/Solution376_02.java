package com.noob.algorithm.daily.plan03.hot100_daily.day07.p020;

/**
 * 🟡 376 摆动序列 - https://leetcode.cn/problems/wiggle-subsequence/description/
 */
public class Solution376_02 {

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

        // 构建2个动态数组（变量）存储以当前元素结尾的最长上升/下降子序列长度（根据序列最后一个差值的正负决定上升/下降概念）
        int[] up = new int[n];
        up[0] = 1;
        up[1] = (nums[1] - nums[0] > 0) ? 2 : 1;

        int[] down = new int[n];
        down[0] = 1;
        down[1] = (nums[1] - nums[0] < 0) ? 2 : 1;

        // 遍历元素
        for (int i = 2; i < n; i++) {
            int curDiff = nums[i] - nums[i - 1];
            if (curDiff > 0) {
                up[i] = down[i - 1] + 1; // 当前趋势为上升，需接在上一个趋势为下降的后面
                down[i] = down[i - 1]; // down 状态平移
            } else if (curDiff < 0) {
                down[i] = up[i - 1] + 1; // 当前趋势为下降，需接在上一个趋势为上升的后面
                up[i] = up[i - 1]; // up 状态平移
            } else {
                // 相等则up、down保持不变
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        // 返回结果
        return Math.max(up[n - 1], down[n - 1]);
    }
}
