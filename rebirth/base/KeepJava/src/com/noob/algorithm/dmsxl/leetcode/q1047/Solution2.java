package com.noob.algorithm.dmsxl.leetcode.q1047;

import java.util.Stack;

/**
 * 1047 删除字符串中的所有相邻重复项
 */
public class Solution2 {

    /**
     * 辅助栈：借助辅助栈（StringBuffer+top指针）存储元素
     * 1.遍历指定元素，每次和栈顶元素进行比较，如果匹配说明出现重复，则直接弹出栈顶元素top（每次入栈、出栈需要注意top指针的同步移动）
     * 2.所有元素遍历完成，最终栈中留存的就是所得（即StringBuffer字符串）
     */
    public String removeDuplicates(String s) {
        // 定义Stringbuffer和top指针模拟辅助栈
        StringBuffer stack = new StringBuffer();
        int top = -1; // 初始化
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (top == -1) {
                stack.append(cur); // 栈为空直接加入数据
                top++; // top 继续指向尾部元素
            } else if (top != -1 && top < s.length()) {
                // 栈不为空，判断top与cur是否匹配
                char curTop = stack.charAt(top);
                if (curTop == cur) {
                    // 存在匹配，执行出栈
                    stack.deleteCharAt(top);
                    top--; // 出栈 top--
                } else {
                    // 不匹配，继续入栈
                    stack.append(cur);
                    top++; // 入栈 top++
                }
            }
        }

        // 返回结果
        return stack.toString();
    }

}
