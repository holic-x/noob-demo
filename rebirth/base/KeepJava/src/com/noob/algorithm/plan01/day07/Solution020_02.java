package com.noob.algorithm.plan01.day07;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 🟢 020 有效的括号
 */
public class Solution020_02 {
    /**
     * 栈：借助栈先入后出的特性
     * 将所有左括号入栈，当遇到非左括号字符的时候则依次弹出并匹配是否满足
     * - 如果校验过程中发现栈顶元素和当前字符无法匹配，则说明这对括号不匹配，不满足题意，可直接退出校验并返回false（说明右括号不匹配或者右括号比较多）
     * - 当所有的字符校验完成，判断栈是否为空，如果栈不为空则说明左括号还有多也不满足题意
     */
    public boolean isValid(String s) {
        // 构建哈希表存储括号匹配关系
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        // 构建辅助栈存储左括号
        Stack<Character> stack = new Stack<>();
        // 遍历字符串序列，校验括号是否匹配
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (map.containsKey(cur)) {
                // 说明是非左括号字符，则从栈中读取（弹出）元素进行匹配
                boolean valid = (!stack.isEmpty()) && (stack.pop() == map.get(cur));
                if (!valid) {
                    return false; // 校验不匹配（存在右括号但栈为空，或者栈顶元素和当前括号不匹配）
                }
            } else {
                // 左括号则正常入栈
                stack.push(cur);
            }
        }
        // 判断左括号是否有剩余,如果有剩余则不匹配
        return stack.isEmpty();
    }
}
