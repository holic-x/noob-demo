package com.noob.algorithm.daily.plan03.hot100_daily.day03.p005;

import java.util.Stack;

/**
 * 🟡 150 逆波兰表达式求值 - https://leetcode.cn/problems/evaluate-reverse-polish-notation/description/
 */
public class Solution150_01 {

    /**
     * 概要：给定一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式，计算该表达式
     * 思路分析：
     * - 表达式包括：运算符、操作数
     * - 遇到操作数正常入栈
     * - 遇到操作符则从栈中取出2个元素（分左、右操作数概念），计算操作结果后将结果入栈
     */
    public int evalRPN(String[] tokens) {
        // 定义栈存储操作数
        Stack<Integer> stack = new Stack<>();

        // 遍历tokens元素
        for (String token : tokens) {
            // 如果为操作符号
            if ("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token)) {
                // 根据不同的操作符号处理
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
                    default: {
                        System.out.println("其他符号处理");
                    }
                }

                // 将处理结果入栈
                stack.push(res);
            } else {
                // 如果为操作数
                stack.push(Integer.valueOf(token));
            }
        }
        // 最终栈中留存元素即为所得
        return stack.peek();
    }
}
