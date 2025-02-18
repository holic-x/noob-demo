package com.noob.algorithm.daily.archive.plan01.day07;

/**
 * 🟢 020 有效的括号
 */
public class Solution020_01 {
    /**
     * 替换法：()、{}、[] 不断替换
     * 字符串中限定只包括括号字符，因此只要通过不断替换的方式将所有可能的括号对替换为空格，校验最终是否可以完全替换成空字符串
     * 如果可以，则说明括号完全匹配，反之
     */
    public boolean isValid(String s) {
        // 不断替换括号对为空字符串
        while (s.contains("()") || s.contains("{}") || s.contains("[]")) {
            s = s.replace("()", "");
            s = s.replace("{}", "");
            s = s.replace("[]", "");
        }
        // 校验替换后的结果是否为空字符串
        return s.equals("");
    }
}
