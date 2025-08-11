package com.noob.algorithm.daily.plan03.hot100_daily.day07.p019;

import java.util.Arrays;

/**
 * 🟢 455 分发饼干 - https://leetcode.cn/problems/assign-cookies/description/
 */
public class Solution455_01 {

    /**
     * 思路分析：
     * 从小到大进行分配，让小胃口的吃小饼干，如果当前饼干小胃口的都吃不下，后面大胃王更加不符合
     */
    public int findContentChildren(int[] g, int[] s) {
        // 对数据进行排序
        Arrays.sort(g);
        Arrays.sort(s);

        // 定义遍历指针，分别定位当前分发的饼干索引 和 目标孩子
        int sIdx = 0, gIdx = 0;
        int sLen = s.length, gLen = g.length;

        int cnt = 0; // 可以吃到饼干的小孩子个数

        // 由于饼干和小朋友个数不同，此处需要验证饼干发放完成（所有饼干都验证完成）或者小孩子验证完成作为退出条件
        while (sIdx < sLen && gIdx < gLen) {
            // 判断当前饼干是否符合目前小朋友的胃口
            if (g[gIdx] <= s[sIdx]) {
                cnt++;
                // 指针后移，继续验证下一组
                sIdx++;
                gIdx++;
            } else {
                // 当前饼干不满足目前小朋友的胃口，且由于胃口是小->大顺序排序的，则后面的小朋友更加不符合要求，因此跳过这块饼干继续下一块饼干的验证
                sIdx++;
            }
        }
        // 返回统计结果
        return cnt;
    }
}
