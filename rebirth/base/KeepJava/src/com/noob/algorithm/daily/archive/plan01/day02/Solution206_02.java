package com.noob.algorithm.daily.archive.plan01.day02;

import com.noob.algorithm.dmsxl.baseStructure.tree.ListNode;

import java.util.Stack;

/**
 * 🟢 206 反转链表
 */
public class Solution206_02 {

    // 方法2：遍历+栈
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }

        // 构建辅助栈存储节点
        Stack<ListNode> stack = new Stack();

        // 构建遍历节点
        ListNode cur = head;
        // 遍历head链表，头插（入新链表）
        while (cur != null) {
            stack.push(cur);
            cur = cur.next; // 指针后移
        }


        // 构建虚拟头节点
        ListNode dummy = new ListNode(-1);
        ListNode pointer = dummy; // 新链表构建的辅助遍历指针
        while (!stack.isEmpty()) {
            pointer.next = stack.pop();
            pointer = pointer.next;
        }
        pointer.next = null; // 处理【Error - Found cycle in the ListNode】

        // 返回构建的新链表
        return dummy.next;
    }
}
