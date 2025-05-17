package com.noob.algorithm.daily.plan03.hot100_random.day03.p005;

import java.util.Stack;

/**
 * 🟡 150 逆波兰表达式求值 - https://leetcode.cn/problems/evaluate-reverse-polish-notation/description/
 */
public class Solution150_01 {

    /**
     * 概要：给定一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式，计算该表达式
     * 思路分析：根据不同的token种类选择相应的处理方式（数字、符号）
     */
    public int evalRPN(String[] tokens) {
        // 定义栈存储数字元素
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (isOperator(token)) {
                // 如果当前遍历token为符号，则从栈中取出两个操作数进行计算随后将结果入栈
                int rightNum = stack.pop();
                int leftNum = stack.pop();
                int res = 0;
                switch (token) {
                    case "+": {
                        res = leftNum + rightNum;
                        break;
                    }
                    case "-": {
                        res = leftNum - rightNum;
                        break;
                    }
                    case "*": {
                        res = leftNum * rightNum;
                        break;
                    }
                    case "/": {
                        res = leftNum / rightNum;
                        break;
                    }
                }
                // 将计算结果入栈
                stack.push(res);
            } else {
                // 如果是非符号则视为操作数进行处理
                stack.push(Integer.valueOf(token));
            }
        }
        // 最终计算完成后栈中留存的一个元素即为结果值
        return stack.peek();
    }

    // 校验是否为符号
    private boolean isOperator(String token) {
        return "+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token);
    }
}
