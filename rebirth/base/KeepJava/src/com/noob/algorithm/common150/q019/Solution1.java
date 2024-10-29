package com.noob.algorithm.common150.q019;

import com.noob.algorithm.common150.base.ListNode;

import java.util.Stack;

/**
 * 019 删除链表的倒数第N个节点
 */
public class Solution1 {

    /**
     * 核心：找到待删除节点的前一个节点
     * 借助栈（先进后出）辅助存储，弹出N-2个元素，则第N-1个元素即待删除元素的前一个节点prev，调整节点指针
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 定义虚拟头节点
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;

        // 构建栈辅助存储
        Stack<ListNode> stack = new Stack<>();
        // 遍历元素依次入栈
        while (cur.next != null) {
            stack.push(cur);
            cur = cur.next;
        }

        // 弹出n-2个元素
        for (int i = 0; i < n - 1; i++) {
            stack.pop();
        }
        ListNode prev = stack.pop(); // 第N-1个元素即为待删除元素的上一个节点
        prev.next = prev.next.next; // 删除节点

        // 返回链表
        return dummy.next;
    }

}
