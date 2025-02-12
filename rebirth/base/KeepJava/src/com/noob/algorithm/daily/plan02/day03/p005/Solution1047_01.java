package com.noob.algorithm.daily.plan02.day03.p005;

import java.util.Stack;

/**
 * 🟢 1047 删除字符串中的所有相邻重复项 - https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string/description/
 */
public class Solution1047_01 {

    /**
     * 思路分析：构建栈辅助操作，将字符ch与栈顶元素top进行校验
     * - 如果相同则说明出现连续重复则跳过
     * - 如果不相同则说明不连续重复，可以将其加入栈
     */
    public String removeDuplicates(String s) {
        // 构建栈辅助遍历
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty()) {
                // 比较当前栈顶元素和当前字符
                if (ch != stack.peek()) {
                    stack.push(ch); // 字符不匹配，可继续入栈
                } else {
                    /**
                     * stack.pop();在此处的设定，可结合调试分析
                     * abbaca => abaca (如果不弹出匹配的栈顶字符，则栈中至少会留下连续重复字符中的1个)
                     * abbaca => ca(弹出匹配的栈顶字符，则会将连续的字符一一消除)
                     */
                    stack.pop(); // 字符匹配，弹出栈顶元素（此步将重复项本身也消除）
                }
            } else {
                // 栈为空，没有比较项目，可直接加入
                stack.push(ch);
            }
        }
        // 返回栈中元素构建的字符串
        StringBuffer res = new StringBuffer();
        while (!stack.isEmpty()) {
            res.insert(0, stack.pop());
        }
        // 返回结果
        return res.toString();
    }
}
