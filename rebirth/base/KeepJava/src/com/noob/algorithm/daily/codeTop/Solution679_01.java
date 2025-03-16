package com.noob.algorithm.daily.codeTop;

import java.util.ArrayList;
import java.util.List;

/**
 * 🔴 679.24 点游戏 - https://leetcode.cn/problems/24-game/description/
 */
public class Solution679_01 {

    private static final double TARGET = 24;
    private static final double EPISLON = 1e-6;

    public boolean judgePoint24(int[] cards) {
        return helper(new double[]{cards[0], cards[1], cards[2], cards[3]});
    }

    private boolean helper(double[] nums) {
        if (nums.length == 1) return Math.abs(nums[0] - TARGET) < EPISLON;
        // 每次选择两个不同的数进行回溯
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                // 将选择出来的两个数的计算结果和原数组剩下的数加入 next 数组
                double[] next = new double[nums.length - 1];
                for (int k = 0, pos = 0; k < nums.length; k++) if (k != i && k != j) next[pos++] = nums[k];
                for (double num : calculate(nums[i], nums[j])) {
                    next[next.length - 1] = num;
                    if (helper(next)) return true;
                }
            }
        }
        return false;
    }

    private List<Double> calculate(double a, double b) {
        List<Double> list = new ArrayList<>();
        list.add(a + b);
        list.add(a - b);
        list.add(b - a);
        list.add(a * b);
        if (!(Math.abs(b) < EPISLON)) list.add(a / b);
        if (!(Math.abs(a) < EPISLON)) list.add(b / a);
        return list;
    }

}
