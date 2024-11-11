package com.noob.algorithm.dmsxl.q151;

import java.util.Stack;

/**
 * 151 反转字符串中的单词
 */
public class Solution3 {
    // 字符串切割、反转数组、拼接
    public String reverseWords(String s) {
        // 1.对字符串进行首尾去除空格处理
        s = s.trim();
        // 2.自定义分割方法遍历字符串入栈
        Stack<String> stack = new Stack<>();

        // 找到单词的起点和终点入栈
        StringBuffer word = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (Character.isLetter(cur) || Character.isDigit(cur)) {
                // 遇到字符则拼接
                word.append(cur);
            } else if (cur == ' ') {
                // 遇到空格说明遍历到当前单词的尾部，构成一个完整的单词(且非空)，需入栈并重置临时处理区
                if (!"".equals(word.toString())) {
                    stack.push(word.toString()); // 入栈
                    word = new StringBuffer(); // 重置
                }
            }
        }
        // 将最后一个缓冲区数据入栈
        stack.push(word.toString());

        // 3.依次弹出栈元素
        StringBuffer res = new StringBuffer();
        int cnt = 0;
        int size = stack.size();
        while (!stack.isEmpty()) {
            res.append(stack.pop());
            cnt++;
            if (cnt != size) {
                res.append(" ");
            }
        }

        // 返回结果
        return res.toString();
    }

    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        solution3.reverseWords("the sky is blue");
    }
}
