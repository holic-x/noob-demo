package com.noob.algorithm.daily.plan03.hot100_daily.day02.p004;

/**
 * LCR 182 动态口令 - https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/description/
 */
public class Solution182_02 {
    /**
     * 将前target个字符移动到password末尾
     * 思路分析：字符串截取概念
     */
    public String dynamicPassword(String password, int target) {
        int pLen = password.length();
        if (target > pLen) {
            return password;
        }

        // 分别截取指定位置的字符串为两部分，随后拼接分拆部分
        String preStr = password.substring(0, target);
        String postStr = password.substring(target, pLen);

        // 拼接数据
        String ans = String.join("", new String[]{postStr, preStr});

        // 返回处理结果
        return ans;
    }
}
