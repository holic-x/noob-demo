package com.noob.algorithm.daily.archive.plan02.hot100.day03.p005;

import java.util.Stack;

/**
 * 🟢 1047 删除字符串中的所有相邻重复项 - https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string/description/
 */
public class Solution1047_02 {
    /**
     * 思路分析：基于StringBuffer和top指针模拟栈操作,top随着字符串序列的动态变化而调整，始终指向最后一个元素
     */
    public String removeDuplicates(String s) {
        // 构建StringBuffer模拟栈操作
        StringBuffer sb = new StringBuffer();
        int top = -1; // top指针指向sb字符串序列的最后一个元素（相当于模拟指向栈顶元素）
        // 遍历字符串序列s
        for (char ch : s.toCharArray()) {
            if (top != -1) {
                // 如果top指向元素索引有效（说明模拟栈不为空），则校验栈顶元素
                if (sb.charAt(top) == ch) {
                    // 元素匹配，说明出现连续重复，移除top指向元素且跳过当前ch(即当前ch不需要入栈)
                    sb.deleteCharAt(top); // 移除top指向的"栈顶"元素
                    top--;
                } else {
                    // 元素不匹配，字符入栈
                    sb.append(ch);
                    top++;
                }
            } else {
                // 如果top指向索引无效（说明模拟栈为空）则无需校验，直接载入字符
                sb.append(ch);
                top++;
            }
        }
        // 返回结果
        return sb.toString();
    }
}
