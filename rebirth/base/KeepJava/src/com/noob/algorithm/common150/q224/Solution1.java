package com.noob.algorithm.common150.q224;

import java.util.Stack;

/**
 * 224 基本计算器
 */
public class Solution1 {

    /**
     * 将操作转化为加法操作（减去一个数等价于加上一个负数）
     */
    public int calculate(String s) {
        // 使用栈存储字符串表达式的数字
        Stack<Integer> stack = new Stack<>();
        // 定义正负标记
        int sign = 1; // 1 表示正数
        // 临时保存计算结果
        int tempRes = 0;

        // 遍历字符串表达式，然后进行解析
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char cur = s.charAt(i);
            // 判断当前字符
            if (Character.isDigit(cur)) {
                int curVal = Integer.valueOf(cur - '0'); // 需要将字符转数字
                // 如果是数字，则需判断其后一位是否存在（直到遇到非数字），组成一个完整的数字
                while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) {
                    // 拼接更新当前的数字
                    curVal = curVal * 10 + Integer.valueOf(s.charAt(i + 1) - '0');
                    // 计算完成，i 继续往前
                    i++;
                }
                // 将结果进行累加
                tempRes = tempRes + sign * curVal;
            } else if (cur == '+' || cur == '-') {
                // 如果是+/-符号，则需更新sign
                sign = (cur == '+') ? 1 : -1; // sign=1 表示加一个正数,sign = -1 表示加一个负数
            } else if (cur == '(') {
                // 如果遇到左括号，则需先将前面的累计结果和符号状态入栈并初始化一个新的res和sigh标记用于计算括号内表达式的值
                stack.push(tempRes);
                stack.push(sign);
                // 初始化
                tempRes = 0;
                sign = 1;
            } else if (cur == ')') {
                // 如果遇到右括号，则说明需要将栈中的元素取出然后进行计算(先取出符号，后取出上一个操作数)
                int prevSign = stack.pop();
                int prevNum = stack.pop();
                // 更新结果值
                tempRes = prevNum + prevSign * tempRes;
            }
        }
        // 最终返回结果值
        return tempRes;
    }
}
