package com.noob.algorithm.plan_archive.plan02.hot100.day01.p002;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

import java.util.Stack;

/**
 * 🟡 019 删除链表的倒数第N个节点 - https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/
 */
public class Solution019_02 {

    /**
     * 思路分析：模拟法，借助栈辅助遍历（链表节点入栈）
     * 栈思路：遍历节点入栈，随后弹出n个节点，第n+1个节点即为待删除项
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 构建虚拟头节点
        ListNode dummy = new ListNode(-1, head);
        // 定义遍历指针
        ListNode cur = dummy;
        Stack<ListNode> stack = new Stack<>();
        while (cur != null) {
            stack.add(cur);
            cur = cur.next;
        }

        // 遍历节点
        while (n-- > 0) {
            stack.pop();
        }
        ListNode pre = stack.pop();
        if (pre.next != null) {
            pre.next = pre.next.next;
        }

        // 返回处理后的链表
        return dummy.next;
    }
}
