package com.noob.algorithm.plan_archive.plan02.hot100.day07.p019;

/**
 * 🟢 860 柠檬水找零 - https://leetcode.cn/problems/lemonade-change/description/
 */
public class Solution860_01 {

    /**
     * 思路分析：顾客支付5 10 20，一杯柠檬水5元，判断是否可以正确找零
     * 找零思路：基于贪心思路，找零的目的是为了保留更多的零钱来支撑找零操作，因此每次优先选择可以保留更多零钱的方案
     */
    public boolean lemonadeChange(int[] bills) {
        // 定义目前零钱张数：此处只有5 10两种零钱可支持找零操作
        int five = 0, ten = 0;
        // 遍历账单方案进行处理
        for (int num : bills) {
            // 分5 10 20这三种账单情况讨论
            if (num == 5) {
                five++; // 无需找零，5元入账
            } else if (num == 10) {
                // 校验是否可以找零
                if (five <= 0) {
                    return false;
                }
                // 可以找零，则10元入账，5元出账
                ten++;
                five--;
            } else if (num == 20) {
                // 有两种找零方案（5+5+5、5+10），为了尽可能保留更多零钱，优先选择5+10方案
                if (five > 0 && ten > 0) {
                    // 20入账，10+5出账
                    ten--;
                    five--;
                } else if (five >= 3) {
                    // 20入账，5+5+5出账
                    five = five - 3;
                } else {
                    return false;// 其余情况不满足找零条件
                }
            }
        }
        // 所有账单数据处理完成，说明目前可支持找零操作
        return true;
    }

}
