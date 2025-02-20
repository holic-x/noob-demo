package com.noob.algorithm.daily.archive.plan02.hot100.day03.p005;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 🟢 020 有效的括号 - https://leetcode.cn/problems/valid-parentheses/description/
 */
public class Solution020_01 {

    /**
     * 限定字符串序列s只包括括号字符
     * 思路分析：基于栈思路（将左括号依次入栈，遇到右括号则取出最近的左括号进行匹配）
     */
    public boolean isValid(String s) {
        // 构建括号映射关系Map<右括号，左括号>
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        // 构建栈存储左括号
        Stack<Character> stack = new Stack<>();

        // 遍历字符串序列进行校验
        for (char ch : s.toCharArray()) {
            if (map.containsValue(ch)) {
                // 如果是左括号，则直接入栈
                stack.push(ch);
            } else if (map.containsKey(ch)) {
                // 如果是右括号，则从栈中取出元素进行校验
                if (!stack.isEmpty()) {
                    if (map.get(ch).equals(stack.peek())) {
                        // 括号匹配，则弹出元素，继续进行校验
                        stack.pop();
                    } else {
                        return false; // 括号不匹配
                    }
                } else {
                    // 如果栈为空，则说明当前字符无匹配的左括号
                    return false;
                }
            }
        }
        // 如果所有元素遍历完成，且最终stack为空，则说明括号完全匹配
        return stack.isEmpty();
    }
}
