package com.noob.algorithm.daily.archive.plan01.day40;

import java.util.Arrays;

/**
 * 🟡 739 每日温度 - https://leetcode.cn/problems/daily-temperatures/description/
 */
public class Solution739_01 {

    /**
     * 思路分析：模拟法
     * 遍历每个温度，判断其后比它高的温度出现在哪个位置
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] t = new int[n];
        Arrays.fill(t, 0); // 初始化为0
        // 外层遍历定位每个温度，内层遍历寻找比当前温度大的下一个温度
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (temperatures[j] > temperatures[i]) {
                    t[i] = j - i;
                    break; // 寻找到下一个更高的温度，填充结果并跳出当次循环
                }
            }
        }
        // 返回结果
        return t;
    }
}
