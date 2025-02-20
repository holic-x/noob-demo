package com.noob.algorithm.plan_archive.plan02.hot100.day03.p005;

import java.util.Stack;

/**
 * 🟡 150 逆波兰表达式求值 - https://leetcode.cn/problems/evaluate-reverse-polish-notation/description/
 */
public class Solution150_01 {

    /**
     * 思路分析：基于栈辅助操作，栈中存储操作数，如果遇到操作符号则取出两个操作数进行处理(加减乘除)后再将结果入栈
     * - 依次类推，直到所有tokens遍历完成，最终栈中留存的即为结果
     */
    public int evalRPN(String[] tokens) {

        // 构建栈辅助操作
        Stack<Integer> stack = new Stack<>();
        // 遍历tokens集合（操作数入栈、操作符号则进一步处理）
        for (String token : tokens) {
            if ("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token)) {
                // 从栈中取出两个操作数进行处理(基于栈先进后出，因此取数顺序为右操作数、左操作数)
                int rightVal = stack.pop();
                int leftVal = stack.pop();
                int res = 0;
                switch (token) {
                    case "+": {
                        res = leftVal + rightVal;
                        break;
                    }
                    case "-": {
                        res = leftVal - rightVal;
                        break;
                    }
                    case "*": {
                        res = leftVal * rightVal;
                        break;
                    }
                    case "/": {
                        res = leftVal / rightVal;
                        break;
                    }
                    default: {
                        // 其余操作符
                        break;
                    }
                }
                // 将操作处理后的结果数入栈
                stack.add(res);
            } else {
                // 其余字符为有效的操作数，直接入栈
                stack.push(Integer.valueOf(token));
            }
        }
        // 最终栈留存的是最终的结果
        return stack.pop();
    }
}
