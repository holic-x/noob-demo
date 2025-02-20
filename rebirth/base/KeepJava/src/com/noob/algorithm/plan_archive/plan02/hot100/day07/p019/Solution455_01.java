package com.noob.algorithm.plan_archive.plan02.hot100.day07.p019;

import java.util.Arrays;

/**
 * 🟢 455 分发饼干 - https://leetcode.cn/problems/assign-cookies/description/
 */
public class Solution455_01 {

    /**
     * 思路分析：g[]胃口值、s[]饼干尺寸，只有s[]满足(大于等于)g[]才可以满足胃口
     */
    public int findContentChildren(int[] g, int[] s) {
        // 贪心思路：将g、s从小到大进行排序，小饼干给小胃口的孩子，大饼干给大胃口的孩子
        Arrays.sort(g);
        Arrays.sort(s);
        int gLen = g.length, sLen = s.length;
        // 定义指针分别用于指向当前待分配小孩胃口、当前待分配饼干
        int gPointer = 0, sPointer = 0;
        int cnt = 0;
        while (gPointer < gLen && sPointer < sLen) {
            // 如果饼干满足分配条件，则直接分配
            if (s[sPointer] >= g[gPointer]) {
                cnt++;
                // 指针移动
                gPointer++;
                sPointer++;
            } else {
                // 找下一块饼干（当前饼干不能满足目前最小胃口的小孩，则更不可能满足后面的孩子）
                sPointer++;
            }
        }
        // 返回分配结果
        return cnt;
    }
}
