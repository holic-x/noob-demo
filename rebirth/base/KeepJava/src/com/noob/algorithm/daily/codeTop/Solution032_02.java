package com.noob.algorithm.daily.codeTop;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 🔴 032 最长有效括号 - https://leetcode.cn/problems/longest-valid-parentheses/
 */
public class Solution032_02 {
    /**
     * 思路分析：栈
     */
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        char[] chs = s.toCharArray();
        // 定义栈存储下标
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // 初始化栈，用于处理边界情况
        // 遍历字符串序列
        for (int i = 0; i < chs.length; i++) {
            if ('(' == chs[i]) {
                stack.push(i); // 将'('的索引入栈
            } else {
                // 取出栈顶元素（表示进行匹配）
                stack.pop();
                // 校验栈是否为空
                if (stack.isEmpty()) {
                    stack.push(i); // 栈为空，则将当前元素下标入栈（更新栈底元素）
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());// 栈不为空，则更新最长的有效括号序列
                }
            }
        }
        // 返回结果
        return maxLen;
    }

    public static void main(String[] args) {
        String s = ")()())";
        Solution032_02 solution = new Solution032_02();
        solution.longestValidParentheses(s);
    }

}
