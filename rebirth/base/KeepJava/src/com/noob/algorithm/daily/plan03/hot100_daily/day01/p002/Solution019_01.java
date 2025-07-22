package com.noob.algorithm.daily.plan03.hot100_daily.day01.p002;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

import java.util.Stack;

/**
 * 🟡 019 删除链表的倒数第N个节点 - https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/
 */
public class Solution019_01 {

    /**
     * 思路分析：链表删除寻找节点的前置节点：pre.next = pre.next.next
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 构建辅助栈处理元素
        Stack<ListNode> stack = new Stack<>();
        ListNode dummy = new ListNode(-1, head);
        ListNode point = dummy;
        while (point != null) {
            stack.push(point);
            point = point.next;
        }

        // 倒数第N个节点，则此处寻找倒数第N-1个节点（此处需注意虚拟头节点）
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        ListNode pre = stack.pop();
        if (pre != null && pre.next != null) {
            pre.next = pre.next.next;
        }
        // 返回处理后的节点
        return dummy.next;
    }
}
