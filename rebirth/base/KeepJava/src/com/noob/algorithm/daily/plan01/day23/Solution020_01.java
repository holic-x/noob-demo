package com.noob.algorithm.daily.plan01.day23;

/**
 * 🟢 020 有效的括号 - https://leetcode.cn/problems/valid-parentheses/description/
 */
public class Solution020_01 {

    /**
     * 校验括号对是否有效：替换法思路（成对替换）
     */
    public boolean isValid(String s) {
        // 校验如果存在括号对则不断替换
        while (s.contains("{}") || s.contains("()") || s.contains("[]")) {
            s = s.replace("{}", "");
            s = s.replace("()", "");
            s = s.replace("[]", "");
        }
        // 校验最终替换的结果是否为空字符串，如果是则括号完全匹配
        return s.equals("");
    }

}
