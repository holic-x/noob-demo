package com.noob.algorithm.daily.plan02.day03.p005;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 🟢 020 有效的括号 - https://leetcode.cn/problems/valid-parentheses/description/
 */
public class Solution020_02 {

    /**
     * 限定字符串序列s只包括括号字符
     * 思路分析：基于替换思路，如果存在范围内的括号对则不断替换为空
     */
    public boolean isValid(String s) {
        // 如果s中存在括号对()\{}\[]则不断将其替换为空，最终得到空字符串则说明括号匹配，如果不为空则说明还有没匹配的括号
        while (s.contains("()") || s.contains("[]") || s.contains("{}")) {
            // 替换指定范围内的括号对
            s = s.replace("()", "");
            s = s.replace("[]", "");
            s = s.replace("{}", "");
        }
        // 校验替换处理后的字符串序列
        return "".equals(s);
    }
}
