package com.noob.algorithm.plan_archive.plan02.hot100.day10.p032;

/**
 * 🟡 647 回文字串（统计回文字串的数目）
 */
public class Solution647_01 {

    /**
     * 思路分析：模拟法（遍历每个字串，校验其是否为回文字串）
     */
    public int countSubstrings(String s) {
        int n = s.length();
        int cnt = 0;
        // 双层循环遍历处理
        for (int i = 0; i < n; i++) { // 外层确定起点
            for (int j = i; j < n; j++) { // 内层确定终点
                if (isHuiWen(s, i, j)) {
                    cnt++;
                }
            }
        }
        // 返回结果
        return cnt;
    }

    // 校验回文字串
    private boolean isHuiWen(String str, int left, int right) {
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }
}
