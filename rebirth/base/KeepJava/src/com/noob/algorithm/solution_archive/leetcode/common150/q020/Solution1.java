package com.noob.algorithm.solution_archive.leetcode.common150.q020;

/**
 * 020 成对的括号
 */
public class Solution1 {

    // 替换法：括号是成对出现的，如果成对进行替换，到最后只剩下空字符串，则括号有效
    public boolean isValid(String s) {
        while(s.contains("()")|| s.contains("{}") || s.contains("[]")){
            // 替换成对的括号
            s = s.replace("()","");
            s = s.replace("{}","");
            s = s.replace("[]","");
        }

        // 如果最终返回空字符串，则说明括号有效
        return s.isEmpty();
    }
}
