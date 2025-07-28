package com.noob.algorithm.daily.plan03.hot100_daily.day03.p005;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;

/**
 * 🟢 020 有效的括号 - https://leetcode.cn/problems/valid-parentheses/description/
 */
public class Solution020_02 {

    // 定义括号对
    HashSet<String> set = new HashSet<String>() {
        {
            add("()");
            add("{}");
            add("[]");
        }
    };

    /**
     * 概要：限定字符串序列s只包括括号字符，给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效
     * 思路分析：
     * - 替换消除法
     */
    public boolean isValid(String s) {

        // 替换消除：如果存在匹配的括号对则依次消除
        while (hasMatch(s)) {
            // 消除
            s = s.replace("()", "");
            s = s.replace("{}", "");
            s = s.replace("[]", "");
        }

        // 如果最终替换消除后的字符串为空则表示括号完全匹配
        return s.isEmpty();
    }

    // 校验是否存在括号对
    private boolean hasMatch(String s) {
        for (String pair : set) {
            if (s.contains(pair)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution020_02 s = new Solution020_02();
        s.isValid("()");
    }
}
