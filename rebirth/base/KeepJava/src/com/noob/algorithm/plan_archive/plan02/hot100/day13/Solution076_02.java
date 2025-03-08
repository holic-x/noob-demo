package com.noob.algorithm.plan_archive.plan02.hot100.day13;

import java.util.HashMap;
import java.util.Map;

/**
 * 🔴 076 最小覆盖子串 - https://leetcode.cn/problems/minimum-window-substring/
 */
public class Solution076_02 {

    /**
     * 滑动窗口思路
     */
    public String minWindow(String s, String t) {
        int sLen = s.length();
        // 定义滑动窗口
        int ansLeft = -1, ansRight = sLen; // 记录结果（记录满足覆盖条件的最小子串信息）
        int[] cntS = new int[128]; // s 子串字母的出现次数（此处包括大小写字母的匹配，下标表示对应字母的ASCII码）
        int[] cntT = new int[128]; // t 中字母的出现次数

        // 统计字符串t的字母出现次数
        for (char ch : t.toCharArray()) {
            cntT[ch]++;
        }

        // 定义滑动窗口，遍历字符串s,处理子串
        int winLeft = 0;
        for (int winRight = 0; winRight < sLen; winRight++) {
            // 将窗口右端点加入子串
            cntS[s.charAt(winRight)]++;
            // 校验当前子串是否满足覆盖条件，如果持续满足则需不断将窗口左端点字符移出，以争取获得更短的满足覆盖条件的子串
            while (isValid(cntS, cntT)) {
                // 更新最小覆盖子串
                if (winRight - winLeft < ansRight - ansLeft) {
                    ansRight = winRight;
                    ansLeft = winLeft;
                }
                // 将左端点字符移出子串
                cntS[s.charAt(winLeft)]--; // 计数处理
                winLeft++; // 指针移动
            }
        }

        // 返回结果
        return ansLeft == -1 ? "" : s.substring(ansLeft, ansRight + 1);
    }


    // 校验子串是否覆盖（s子串覆盖t则需满足s所有的字符个数要大于等于t中对应字符个数）
    public boolean isValid(int[] cntS, int[] cntT) {
        // 依次遍历校验(摘取大小写进行校验)
        for (int i = 'A'; i <= 'Z'; i++) {
            if (cntS[i] < cntT[i]) {
                return false;
            }
        }
        for (int i = 'a'; i <= 'z'; i++) {
            if (cntS[i] < cntT[i]) {
                return false;
            }
        }
        return true;
    }

}
