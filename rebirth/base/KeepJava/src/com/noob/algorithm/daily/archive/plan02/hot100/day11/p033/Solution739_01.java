package com.noob.algorithm.daily.archive.plan02.hot100.day11.p033;

/**
 * 🟡 739 每日温度 - https://leetcode.cn/problems/daily-temperatures/description/
 */
public class Solution739_01 {

    /**
     * 每日温度：下一个更高的温度出现在几日后
     * 模拟法：双层循环检索
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];

        // 遍历温度信息
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (temperatures[i] < temperatures[j]) { // 出现了更高的温度
                    ans[i] = j - i;
                    break; // 找到第1个满足的温度，填充结果后退出
                }
            }
        }
        // 返回结果
        return ans;
    }

}
