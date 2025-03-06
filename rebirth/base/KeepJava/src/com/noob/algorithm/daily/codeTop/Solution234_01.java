package com.noob.algorithm.daily.codeTop;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

import java.util.Stack;

/**
 * 🟢 234 回文链表 - https://leetcode.cn/problems/palindrome-linked-list/description/
 */
public class Solution234_01 {
    /**
     * 思路分析：判断回文，正序和逆序一致
     */
    public boolean isPalindrome(ListNode head) {
        // 定义指针辅助遍历
        ListNode p = head;
        Stack<Integer> stack = new Stack<>();
        while (p != null) {
            stack.push(p.val);
            p = p.next;
        }

        // 再次同时遍历链表和栈
        ListNode cur = head;
        while (!stack.isEmpty()) {
            if (cur.val != stack.pop()) {
                return false;
            }
            cur = cur.next;
        }

        // 校验通过
        return true;
    }
}
