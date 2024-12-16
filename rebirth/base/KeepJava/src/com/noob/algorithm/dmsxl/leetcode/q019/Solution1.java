package com.noob.algorithm.dmsxl.leetcode.q019;

import com.noob.algorithm.dmsxl.baseStructure.tree.ListNode;

import java.util.Stack;

/**
 * 019 删除链表的倒数第N个节点
 */
public class Solution1 {

    // 迭代：辅助栈（倒数第N个节点，对于栈而言为第N个）
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 辅助栈
        Stack<ListNode> stack = new Stack<>();

        // 虚拟头节点
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;

        // 遍历链表
        while (cur != null) {
            stack.push(cur);
            cur = cur.next; // 指针后移
        }

        // 遍历栈，找到待删除节点的前一个节点，即栈对应的N-1
        for (int i = 0; i < n; i++) { // 因为构建了一个虚拟头节点，因此要弹出N-1个节点，下一个节点才是N-1
            stack.pop();
        }
        // 取到倒数第N-1
        ListNode prev = stack.peek();
        prev.next = prev.next.next; // 执行删除

        // 返回链表
        return dummy.next;
    }

}
