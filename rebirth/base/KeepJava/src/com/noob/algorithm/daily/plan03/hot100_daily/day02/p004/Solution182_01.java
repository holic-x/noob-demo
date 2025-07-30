package com.noob.algorithm.daily.plan03.hot100_daily.day02.p004;

/**
 * LCR 182 动态口令 - https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/description/
 */
public class Solution182_01 {
    /**
     * 将前target个字符移动到password末尾
     * 思路分析：分段遍历法
     */
    public String dynamicPassword(String password, int target) {
        int pLen = password.length();
        if (target > pLen) {
            return password;
        }

        // 移动字符串（先遍历target位置开始的字符，随后遍历[0,target-1]的位置）
        StringBuffer ans = new StringBuffer();
        for (int i = target; i < pLen; i++) {
            ans.append(password.charAt(i));
        }
        for (int i = 0; i < target; i++) {
            ans.append(password.charAt(i));
        }

        // 返回处理结果
        return ans.toString();
    }
}
