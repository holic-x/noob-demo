package com.noob.algorithm.daily.codeTop;

import com.noob.algorithm.daily.base.ListNode;

import java.util.Stack;

/**
 * 🟢206 反转链表
 */
public class Solution206_01 {

    // 遍历法：栈或者队列等集合处理
    public ListNode reverseList(ListNode head) {

        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head; // 构建指针遍历head链表
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        // 读取栈的内容，构建新链表
        ListNode dummy = new ListNode(-1);
        ListNode pointer = dummy;
        while (!stack.isEmpty()) {
            pointer.next = stack.pop();
            pointer = pointer.next;
        }
        pointer.next = null; // 处理尾节点 cycle问题

        // 返回构建的链表
        return dummy.next;
    }
}
