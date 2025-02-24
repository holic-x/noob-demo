package com.noob.algorithm.solution_archive.dmsxl.leetcode.q150;

import java.util.Stack;

/**
 * 150 逆序波兰表达式
 */
public class Solution1 {

    // 辅助栈
    public int evalRPN(String[] tokens) {
        // 定义辅助栈存储操作数
        Stack<Integer> stack = new Stack<>();
        /**
         * 遍历tokens，根据token类型决定操作
         * 1.数字
         * 2.计算符号+-×/
         */
        for (String token : tokens) {
            // 符号判断
            if ("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token)) {
                // 取出栈中的操作数进行操作，随后将操作结果入栈等待下一次运算
                int right = stack.pop(); // 右操作数(后入先出)
                int left = stack.pop(); // 左操作数
                // 根据操作符进行运算
                int res = -1;
                switch (token) {
                    case "+": {
                        res = left + right;
                        break;
                    }
                    case "-": {
                        res = left - right;
                        break;
                    }
                    case "*": {
                        res = left * right;
                        break;
                    }
                    case "/": {
                        res = left / right;
                        break;
                    }
                    default: {
                        // 非指定操作符号
                        break;
                    }
                }
                // 将操作结果入栈
                stack.push(res);
            } else {
                // 对于非操作符即为数字（如果给定的tokens正常的情况下）
                stack.push(Integer.valueOf(token)); // 操作数入栈
            }
        }
        // 最终栈中留存的元素即为res
        return stack.pop();
    }
}
