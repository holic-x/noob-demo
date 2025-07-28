package com.noob.algorithm.daily.plan03.hot100_daily.day03.p005;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 🟢 020 有效的括号 - https://leetcode.cn/problems/valid-parentheses/description/
 */
public class Solution020_01 {

    /**
     * 概要：限定字符串序列s只包括括号字符，给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效
     * 思路分析：
     * - 序列只包括括号，引入栈辅助存储，左括号入栈，右括号匹配校验
     */
    public boolean isValid(String s) {
        // 定义括号映射关系
        Map<Character, Character> map = new HashMap<Character, Character>() {
            {
                put('(', ')');
                put('{', '}');
                put('[', ']');
            }
        };

        // 构建栈辅助存储
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            // 如果是左括号
            if (map.containsKey(ch)) {
                stack.push(ch);
            } else {
                // 非左括号,从栈中弹出元素并匹配校验
                if (stack.isEmpty()) {
                    return false;// 栈为空，说明无匹配括号，校验不通过
                }
                if (ch != map.get(stack.pop())) {
                    return false;
                }
            }
        }

        // 最终所有元素校验完成且栈为空则说明括号完全匹配
        return stack.isEmpty();
    }
}
