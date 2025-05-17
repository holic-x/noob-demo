package com.noob.algorithm.daily.plan03.hot100_random.day02.p004;

/**
 * LCR 182 动态口令 - https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/description/
 */
public class Solution182_01 {
    /**
     * 将前target个字符移动到password末尾
     * 思路分析：
     */
    public String dynamicPassword(String password, int target) {

        // 字符串拼接处理
        StringBuffer buffer = new StringBuffer();
        for (int i = target; i < password.length(); i++) {
            buffer.append(password.charAt(i));
        }

        // 拼接前target个字符
        for (int i = 0; i < target; i++) {
            buffer.append(password.charAt(i));
        }

        // 返回结果
        return buffer.toString();
    }
}
