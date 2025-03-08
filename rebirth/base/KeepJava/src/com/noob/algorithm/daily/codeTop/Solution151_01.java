package com.noob.algorithm.daily.codeTop;

/**
 * 🟡 151 反转字符串中的单词 - https://leetcode.cn/problems/reverse-words-in-a-string/description/
 */
public class Solution151_01 {

    /**
     * eg：s = "the sky is blue " 反转后："blue is sky the"
     */
    public String reverseWords(String s) {
        // ① 首尾去除空格
        s = s.trim();
        // ② 字符串切割
        String[] strs = s.split("\\s+"); //  s.split(" ") 单个空格切分
        // ③ 逆序输出并拼接
        StringBuffer sbuffer = new StringBuffer();
        for (int i = strs.length - 1; i >= 0; i--) {
            sbuffer.append(strs[i]);
            if (i != 0) {
                sbuffer.append(" "); // 拼接符
            }
        }
        // 返回结果
        return sbuffer.toString();
    }

    public static void main(String[] args) {
        Solution151_01 s = new Solution151_01();
        System.out.println(s.reverseWords("the sky is blue "));
    }
}
