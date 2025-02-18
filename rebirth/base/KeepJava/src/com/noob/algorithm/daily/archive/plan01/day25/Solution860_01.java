package com.noob.algorithm.daily.archive.plan01.day25;

/**
 * 🟢 860 柠檬水找零钱 - https://leetcode.cn/problems/lemonade-change/description/
 */
public class Solution860_01 {

    /**
     * 思路分析：按照账单支付顺序遍历，判断目前剩余的零钱是否足以支撑找零
     * - 记录每个零钱的数量，根据账单支付金额进行找零，如果符合找零条件则继续遍历，不符合则退出
     */
    public boolean lemonadeChange(int[] bills) {
        // 记录每个零钱的数量
        int five = 0, ten = 0;
        for (int i = 0; i < bills.length; i++) {
            // 确认账单金额，敲定找零方案
            if (bills[i] == 5) {
                // 无需找零，入账5元
                five++;
            } else if (bills[i] == 10) {
                if (five > 0) {
                    // 入账10元，找零5元
                    ten++;
                    five--;
                } else {
                    return false;
                }
            } else if (bills[i] == 20) {
                // 入账20元，找零15（两种方案：5+10、5+5+5）
                if (five > 0 && ten > 0) {
                    // 优先选择5+10方案（因为5作为基础可以支持更多找零方案）
                    five--;
                    ten--;
                } else if (five >= 3) {
                    five = five - 3;
                } else {
                    return false; // 不满足找零条件
                }
            }
        }
        // 所有账单金额确认完成，满足找零条件
        return true;
    }
}
