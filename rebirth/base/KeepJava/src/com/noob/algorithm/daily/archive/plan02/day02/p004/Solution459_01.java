package com.noob.algorithm.daily.archive.plan02.day02.p004;

/**
 * 🟢 459 重复的子字符串 - https://leetcode.cn/problems/repeated-substring-pattern/description/
 */
public class Solution459_01 {

    /**
     * 校验非空字符串s是否可以由其字串重复多次构成
     * 思路分析：模拟法（检验每个可能的子串，看是否可以重复多次构成s）
     */
    public boolean repeatedSubstringPattern(String s) {
        // 获取字符串s的每个子串并校验
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i + 1; j < s.length(); j++) {
                String subStr = s.substring(i, j);
                if (valid(subStr, s)) {
                    return true;
                }
            }
        }
        // 没有满足的内容
        return false;
    }


    // 校验字串是否可以重复多次构成目标字符串
    private boolean valid(String subStr, String str) {
        /**
         * 可构成目标字符串需满足条件：n 个 subStr 构成 str
         */
        int subLen = subStr.length(), len = str.length();
        if (len % subLen != 0) {
            return false;
        }
        int n = len / subLen;
        // 校验n个subStr是否可构成str
        StringBuffer sb = new StringBuffer();
        while (n-- > 0) {
            sb.append(subStr);
        }
        return str.equals(sb.toString()); // 此处校验字符串是否匹配，注意是sb.toString()拿到字符串数据
    }

    public static void main(String[] args) {
        Solution459_01 solution = new Solution459_01();
        solution.repeatedSubstringPattern("abab");
    }

}
