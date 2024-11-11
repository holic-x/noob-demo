package com.noob.algorithm.dmsxl.q151;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 151 反转字符串中的单词
 */
public class Solution2 {
    // 字符串切割、反转数组、拼接
    public String reverseWords(String s) {
        // 1.对字符串进行首尾去除空格处理
        s = s.trim();
        // 2.切割字符串（一个或多个空格）
        String[] strs = s.split("\\s+"); // 通过正则表达式切割
        // 3.借助工具类进行逆序处理
        List<String> list = Arrays.asList(strs);
        Collections.reverse(list);
        // 4.借助工具类拼接
        return String.join(" ",list);
    }
}
