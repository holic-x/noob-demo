package com.noob.algorithm.dmsxl.leetcode.q123;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 123 买卖股票的最佳时机III
 */
public class Solution1 {

    /**
     * 贪心思路：回归【122 买卖股票的最佳时机II】思路，如果存在正利润，则选择最大的两个正利润
     * todo 此处贪心思路错误，无法覆盖所有情况，需切换到动态规划思路
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        List<Integer> profit = new ArrayList<>();
        for (int i = 1; i < prices.length; i++) {
            // 计算当前利润差
            profit.add(prices[i] - prices[i - 1]);
        }

        // 对利润差进行排序，选择大于0的利润进行收集（从大到小排序）
        Collections.sort(profit, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int max = 0;
        max += profit.get(0) > 0 ? profit.get(0) : 0;
        max += profit.get(1) > 0 ? profit.get(1) : 0;
        // 返回结果
        return max;
    }

}
