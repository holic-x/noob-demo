package com.noob.algorithm.daily.plan03.hot100_random.day03.p005;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 🟢 020 有效的括号 - https://leetcode.cn/problems/valid-parentheses/description/
 */
public class Solution020_01 {

    /**
     * 概要：限定字符串序列s只包括括号字符，给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效
     * 思路分析：栈匹配
     */
    public boolean isValid(String s) {
        // 定义栈辅助校验
        Stack<Character> stack = new Stack<>();

        // 定义括号的匹配对
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        // 校验字符，遇到左括号入栈等待匹配，遇到右括号则取出进行校验，直到元素校验完成或者栈为空
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (map.containsValue(cur)) {
                stack.push(cur);
            } else if (map.containsKey(cur)) {
                // 校验栈中是否存在匹配的括号
                if (stack.isEmpty()) {
                    return false;
                } else {
                    if (map.get(cur) != stack.pop()) {
                        return false;
                    }
                }
            }
        }

        // 最终所有字符校验通过且栈为空则视作恰好匹配
        return stack.isEmpty();
    }
}
