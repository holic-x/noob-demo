package com.noob.algorithm.plan_archive.plan01.day08;

import java.util.Stack;

/**
 * 150 逆序波兰表达式
 */
public class Solution150_01 {

    /**
     * 辅助栈思路：栈中存储操作数字，当遍历遇到操作符则弹出栈的两个操作数并根据操作符计算结果，然后将计算的结果再入栈
     */
    public int evalRPN(String[] tokens) {

        // 构建辅助栈
        Stack<Integer> stack = new Stack<>(); // 栈中存储操作数
        // 遍历元素
        for (String cur : tokens) {
            if ("+".equals(cur) || "-".equals(cur) || "*".equals(cur) || "/".equals(cur)) {
                // 遇到符号则需从栈中取出两个操作数进行处理（先弹出的是右操作数、后弹出的是左操作数）
                int right = stack.pop();
                int left = stack.pop();
                int res = 0; // 存储操作结果
                switch (cur) {
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
                }
                // 将处理结果入栈
                stack.push(res);
            } else {
                stack.push(Integer.valueOf(cur)); // 非符号位则为数字，直接入栈
            }
        }

        // 遍历结束，最终栈中留存的为处理结果
        return stack.peek();
    }


}
