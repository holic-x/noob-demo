package com.noob.algorithm.daily.plan01.day08;

import java.util.Stack;

/**
 * 🟢 1047 删除字符串中的所有相邻重复项
 */
public class Solution1047_02 {

    // 借助StringBuffer模拟栈操作
    public String removeDuplicates(String s) {
        // 构建StringBuffer模拟栈操作
        StringBuffer res = new StringBuffer();
        int top = -1; // 定义top指针始终指向最后一个元素
        for (char cur : s.toCharArray()) {
            /**
             * 栈不为空时，比较栈顶元素与当前遍历元素
             * - 如果相同说明出现了重复元素则弹出栈顶元素(此处需要消除重复的元素，因此重复元素不需要再次入栈)
             * - 否则将cur元素入栈等待校验
             */
            if (top != -1 && res.charAt(top) == cur) {
                res.deleteCharAt(top);
                top--;
            } else {
                res.append(cur);
                top++;
            }
        }

        // 返回结果
        return res.toString();
    }
}
