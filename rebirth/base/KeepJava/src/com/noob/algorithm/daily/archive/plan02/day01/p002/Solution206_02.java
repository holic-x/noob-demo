package com.noob.algorithm.daily.archive.plan02.day01.p002;


import com.noob.algorithm.daily.base.ListNode;

import java.util.Stack;

/**
 * 🟢 206 反转链表 - https://leetcode.cn/problems/reverse-linked-list/
 */
public class Solution206_02 {

    /**
     * 思路分析：迭代法（反向遍历：遍历链表到容器，然后反向遍历构建新链表达到反转的效果）
     */
    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        // 构建栈辅助遍历
        Stack<Integer> stack = new Stack<>();
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        // 依次弹出栈元素，构建新链表
        ListNode dummy = new ListNode(-1);
        ListNode pt = dummy;
        while (!stack.isEmpty()) {
            pt.next = new ListNode(stack.pop());
            pt = pt.next;
        }
        // 返回构建的新链表
        return dummy.next;
    }
}
