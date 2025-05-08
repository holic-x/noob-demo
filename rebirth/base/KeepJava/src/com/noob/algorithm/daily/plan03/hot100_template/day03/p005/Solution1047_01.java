package com.noob.algorithm.daily.plan03.hot100_template.day03.p005;

import java.util.Stack;

/**
 * 🟢 1047 删除字符串中的所有相邻重复项 - https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string/description/
 */
public class Solution1047_01 {

    /**
     * 概要：删除两个相邻且相同的字母
     * 思路分析：
     */
    public String removeDuplicates(String s) {
        // 构建栈辅助存储
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            // 校验栈顶元素与当前遍历元素
            if (!stack.isEmpty()) {
                char top = stack.peek();
                if (top == ch) {
                    // 弹出栈顶元素
                    stack.pop();
                } else {
                    stack.push(ch);
                }
            } else {
                stack.push(ch);
            }
        }

        // 弹出栈内留存元素并拼接为字符串
        StringBuffer buffer = new StringBuffer();
        while (!stack.isEmpty()) {
            buffer.append(stack.pop());
        }

        // 返回结果
        return buffer.reverse().toString();
    }
}
