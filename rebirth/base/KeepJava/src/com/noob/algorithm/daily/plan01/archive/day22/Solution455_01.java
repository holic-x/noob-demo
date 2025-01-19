package com.noob.algorithm.daily.plan01.archive.day22;

import java.util.Arrays;

/**
 * 🟢455 分发饼干 - https://leetcode.cn/problems/assign-cookies/description/
 */
public class Solution455_01 {

    /**
     * 当饼干尺寸满足胃口值的时候可分发，尽可能派发更多的小朋友
     *
     * @param g 胃口值
     * @param s 饼干尺寸
     */
    public int findContentChildren(int[] g, int[] s) {
        int cnt = 0; // 分发计数器
        // 将数组元素从小到大进行排序
        Arrays.sort(g);
        Arrays.sort(s);

        // 定义指针分别遍历两个数组
        int gPointer = 0, sPointer = 0;
        // 所有饼干派完或者所有小朋友都确认完则结束
        while (gPointer < g.length && sPointer < s.length) {
            // 判断当前位置饼干是否匹配小朋友胃口，如果满足则派发饼干，不满足则找下一块饼干
            if (s[sPointer] >= g[gPointer]) {
                cnt++; // 满足派发条件
                sPointer++;
                gPointer++;
            } else {
                sPointer++; // 不满足派发条件，寻找下一个满足的饼干尺寸
            }
        }
        // 返回结果
        return cnt;
    }
}
