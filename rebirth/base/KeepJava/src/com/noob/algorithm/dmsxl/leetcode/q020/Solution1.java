package com.noob.algorithm.dmsxl.leetcode.q020;

/**
 * 020 有效的括号
 */
public class Solution1 {

    // 替换法
    public boolean isValid(String s) {
        // 当目标字符串存在匹配括号则不断进行替换
        while(s.contains("{}") || s.contains("()") || s.contains("[]")){
            // 替换匹配的括号
            s = s.replace("{}","");
            s = s.replace("()","");
            s = s.replace("[]","");
        }
        // 替换操作结束后验证替换后的s是否为空（如果为空说明所有括号匹配，如果不为空说明还有括号没匹配）
        return s.length()==0;
    }

}
