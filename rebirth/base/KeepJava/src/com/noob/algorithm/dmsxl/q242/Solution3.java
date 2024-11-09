package com.noob.algorithm.dmsxl.q242;

import java.util.Arrays;

/**
 * 有效的字母异位词（242）
 */
public class Solution3 {
    // 【3】比较移除法
    public boolean isAnagram(String s, String t) {
        // 如果两个字符串长度不一致则必然非字母异位词
        if (s.length() != t.length()) {
            return false;
        }
        /**
         * 遍历s字符串，判断s中的字符是否在t中存在，如果存在则移除t中的该字符，不存在说明不符合字母异位词条件
         * - 此处可以在循环中判断，遇到不匹配直接返回false
         * - 也可以直接调用替换方法，最后遍历完成校验t的长度是否为0即可
         */
        for (char cur : s.toCharArray()) {
            t = t.replaceFirst(cur + "", ""); // 替换第1个匹配cur字符的元素为空，表示移除
        }

        // 当s遍历完成，如果s与t互为字母异位词，则t应该为空
        return t.length() == 0;
    }

    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        solution3.isAnagram("anagram", "nagaram");
    }
}
