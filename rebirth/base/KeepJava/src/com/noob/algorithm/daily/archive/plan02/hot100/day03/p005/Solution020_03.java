package com.noob.algorithm.daily.archive.plan02.hot100.day03.p005;

import java.util.HashSet;
import java.util.Set;

/**
 * 🟢 020 有效的括号 - https://leetcode.cn/problems/valid-parentheses/description/
 */
public class Solution020_03 {

    /**
     * 限定字符串序列s只包括括号字符
     * 思路分析：基于替换思路，如果存在范围内的括号对则不断替换为空
     */
    public boolean isValid(String s) {
        // 定义要校验的括号序列对
        Set<String> strs = new HashSet() {
            {
                add("()");
                add("[]");
                add("{}");
            }
        };
        // 如果s中存在括号对()\{}\[]则不断将其替换为空，最终得到空字符串则说明括号匹配，如果不为空则说明还有没匹配的括号
        while (valid(strs, s)) {
            // 替换指定范围内的括号对
            for (String str : strs) {
                s = s.replace(str, ""); // 替换
            }
        }
        // 校验替换处理后的字符串序列
        return "".equals(s);
    }

    // 校验指定字符串中是否包括括号序列
    private boolean valid(Set<String> set, String s) {
        for (String str : set) {
            if (s.contains(str)) {
                return true;
            }
        }
        return false;
    }
}
