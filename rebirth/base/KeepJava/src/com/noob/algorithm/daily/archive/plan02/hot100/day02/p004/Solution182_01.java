package com.noob.algorithm.daily.archive.plan02.hot100.day02.p004;

/**
 * LCR 182 动态口令 - https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/description/
 */
public class Solution182_01 {
    /**
     * 将前target个字符移动到password末尾
     * 思路分析：模拟法（顺序遍历思路）
     */
    public String dynamicPassword(String password, int target) {
        int n = password.length();
        StringBuffer res = new StringBuffer();
        for (int i = target; i < n; i++) { // [target,n]
            res.append(password.charAt(i));
        }
        // [0,target)
        for (int i = 0; i < target; i++) {
            res.append(password.charAt(i));
        }
        return res.toString();
    }
}
