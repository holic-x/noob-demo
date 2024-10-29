package com.noob.algorithm.common150.q150;

import java.util.Stack;

/**
 * 150 逆波兰表达式求值
 */
public class Solution1 {

    /**
     * 遍历数组元素，借助栈辅助操作
     * 1.如果是数字则入栈
     * 2.如果是操作符，则需从栈中取出两个元素进行运算，随后将得到的结果入栈
     */
    public int evalRPN(String[] tokens) {
        // 定义栈用作操作辅助
        Stack<Integer> stack = new Stack<>();
        // 遍历表达式
        for (String token : tokens) {
            // 如果是操作数
            if ("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token)) {
                // 取出栈元素
                int right = stack.pop();
                int left = stack.pop();
                int res;
                switch (token) {
                    case "+": {
                        res = left + right;
                        stack.push(Integer.valueOf(res));
                        break;
                    }
                    case "-": {
                        res = left - right;
                        stack.push(Integer.valueOf(res));
                        break;
                    }
                    case "*": {
                        res = left * right;
                        stack.push(Integer.valueOf(res));
                        break;
                    }
                    case "/": {
                        res = left / right;
                        stack.push(Integer.valueOf(res));
                        break;
                    }
                }
            } else {
                // 其他内容，数字处理直接压栈
                stack.push(Integer.valueOf(token));
            }
        }
        // 遍历完成，最终得到的栈中只有一个元素
        return stack.peek();
    }
}
