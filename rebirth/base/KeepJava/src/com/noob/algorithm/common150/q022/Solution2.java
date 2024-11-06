package com.noob.algorithm.common150.q022;

import java.util.ArrayList;
import java.util.List;

/**
 * 022 括号生成
 */
public class Solution2 {

    List<String> ans = new ArrayList<>(); // 定义结果集
    StringBuilder buffer = new StringBuilder(); // 用于跟踪当前括号序列

    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }

        // n 不为0，递归遍历所有括号序列并校验序列的有效性
        generate(n,0,0);
        return ans;
    }

    /**
     * 定义辅助方法生成括号序列并校验序列的有效性(优化点：根据左括号的个数来决定放右括号)
     * @param n     括号对数
     * @param left  左括号个数
     * @param right 右括号个数
     */
    public void generate(int n, int left, int right) {
        // 递归出口：当前序列长度等于2*n（n表示括号对数）
        if (buffer.length() == 2 * n) {
            // 在递归的过程中控制了括号序列的有序性，因此此处只需要校验对数是否满足
            ans.add(buffer.toString());
        } else {

            // 判断左括号是否有n个，如果不足则可以继续放左括号
            if (left < n) {
                buffer.append("("); // 尝试加入左括号
                generate(n, left + 1, right); // 递归
                buffer.deleteCharAt(buffer.length() - 1); // 复原现场
            }

            // 根据左括号校验右括号
            if (right < left) { // 如果右括号比左括号个数小，则继续补足左括号
                buffer.append(")"); // 尝试加入右括号
                generate(n, left, right + 1);
                buffer.deleteCharAt(buffer.length() - 1); // 复原现场
            }
        }
    }
}
