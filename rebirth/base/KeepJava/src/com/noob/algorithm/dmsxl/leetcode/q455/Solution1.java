package com.noob.algorithm.dmsxl.leetcode.q455;

import java.util.Arrays;

/**
 * 455 分发饼干
 */
public class Solution1 {
    public int findContentChildren(int[] g, int[] s) {
        // 记录满足派发条件的个数
        int cnt = 0;

        // 排序
        Arrays.sort(g);
        Arrays.sort(s);

        // 定义双指针：分别指向两个数组遍历位置
        int gPointer = 0, sPointer = 0;
        // 饼干和小朋友的数量不一定一一对应，因此两个当中一个完结视为结束
        while (sPointer < s.length && gPointer < g.length) {
            // 用最小的饼干投喂胃口最小的小朋友
            if (s[sPointer] >= g[gPointer]) {
                // 满足条件则进行投喂，然后继续下一块饼干的派发
                cnt++;
                // 继续下一块饼干、下一个小朋友
                sPointer++;
                gPointer++;
            } else {
                // 当前饼干不满足投喂这个小朋友，拿下一块试试
                sPointer++;
            }
        }

        // 返回结果
        return cnt;
    }
}
