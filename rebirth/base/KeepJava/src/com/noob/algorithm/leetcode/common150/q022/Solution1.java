package com.noob.algorithm.leetcode.common150.q022;

import java.util.ArrayList;
import java.util.List;

/**
 * 022 括号生成
 */
public class Solution1 {

    List<String> ans = new ArrayList<>(); // 定义结果集
    StringBuilder buffer = new StringBuilder(); // 用于跟踪当前括号序列

    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }

        // n 不为0，递归遍历所有括号序列并校验序列的有效性
        generate(n);
        return ans;
    }

    // 定义辅助方法生成括号序列并校验序列的有效性
    public void generate(int n) {
        // 递归出口：当前序列长度等于2*n（n表示括号对数）
        if (buffer.length() == 2 * n) {
            // 校验括号序列是否有效
            if (isValid(buffer.toString())) {
                ans.add(buffer.toString());
            }
        } else {
            // 加入左括号或者右括号，然后深度遍历到底部校验括号序列
            buffer.append("("); // 尝试加入左括号
            generate(n);
            buffer.deleteCharAt(buffer.length() - 1); // 复原现场

            buffer.append(")"); // 尝试加入右括号
            generate(n);
            buffer.deleteCharAt(buffer.length() - 1); // 复原现场
        }
    }

    // 校验序列的括号有效性（balance）
    public boolean isValid(String str) {
        int balance = 0; // 定义平衡参数校验括号序列的有效性
        for (char c : str.toCharArray()) {
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }

}
