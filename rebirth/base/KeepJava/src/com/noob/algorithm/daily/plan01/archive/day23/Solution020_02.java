package com.noob.algorithm.daily.plan01.archive.day23;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 🟢 020 有效的括号 - https://leetcode.cn/problems/valid-parentheses/description/
 */
public class Solution020_02 {

    /**
     * 思路：基于栈辅助遍历（先入后出思路），左括号入栈，遇到有括号则依次取出校验
     */
    public boolean isValid(String s) {

        // 定义字符串匹配集合
        Map<Character, Character> map = new HashMap<Character, Character>() {
            {
                put(')', '(');
                put('}', '{');
                put(']', '[');
            }
        };


        // 构建栈辅助遍历
        Stack<Character> stack = new Stack<>();

        // 遍历字符串序列
        for (int i = 0; i < s.length(); i++) {
            char curCh = s.charAt(i);
            if (map.containsValue(curCh)) {
                // 左括号则入栈
                stack.push(curCh);
            } else {
                // 右括号则校验当前栈中是否有匹配元素（如果并不存在或者不匹配则说明括号不匹配）
                if (stack.isEmpty() || map.get(curCh) != stack.pop()) {
                    return false;
                } // 校验通过则继续下一个符号遍历
            }
        }

        // 所有序列校验完成且栈为空则说明完全匹配
        return stack.isEmpty();
    }

}
