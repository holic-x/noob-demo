package com.noob.algorithm.hot100.q206;

import com.noob.algorithm.hot100.q160.ListNode;

import java.util.Stack;

/**
 * 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表
 */
public class Solution1 {

    public ListNode reverseList(ListNode head) {
        // 反转思路：引入栈 先进后出，依次遍历链表元素进行入栈，然后遍历栈

        // 定义栈
        Stack<ListNode> stack = new Stack<ListNode>();
        while(head != null) {
            // 入栈
            stack.push(head);
            // 指针向后移动
            head = head.next;
        }

        // 遍历栈（依次出栈，并拼接列表）
        ListNode newHead = new ListNode(0); // 定义虚拟头节点
        ListNode cur = newHead; // 定义指针
        while(stack.size() > 0) {
            cur.next = stack.pop();
            cur = cur.next; // 指针后移
        }
        cur.next = null;

        // 返回结果
        return newHead.next;
    }

}
