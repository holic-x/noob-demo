package com.noob.algorithm.daily.plan03.hot100_daily.day03.p005;

import java.util.Stack;

/**
 * 🟢 1047 删除字符串中的所有相邻重复项 - https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string/description/
 */
public class Solution1047_01 {

    /**
     * 概要：删除两个相邻且相同的字母
     * 思路分析：
     * - 引入栈辅助处理：栈顶元素与当前遍历目标元素，如果栈为空或者栈顶元素与当前遍历元素不匹配的话则将元素加入栈，否则做消除（直接弹出）
     */
    public String removeDuplicates(String s) {
        // 构建辅助栈处理
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (stack.isEmpty() || stack.peek() != ch) {
                stack.push(ch);
            } else {
                // 栈顶元素匹配，直接弹出
                stack.pop();
            }
        }
        // 返回栈中剩余元素
        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop()); // 插入元素到指定位置
        }
        return sb.toString();
    }
}
