package com.noob.algorithm.daily.plan03.hot100_random.day01.p002;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

import java.util.Stack;

/**
 * 🟡 019 删除链表的倒数第N个节点 - https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/
 */
public class Solution019_01 {

    /**
     * 思路分析：
     * 删除链表的倒数第N个节点，可以基于遍历的思路寻找到倒数第N+1个节点，然后执行删除操作（构建虚拟头节点执行删除操作）
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 定义虚拟头节点
        ListNode dummy = new ListNode(-1, head);

        // 遍历链表
        ListNode cur = dummy;
        Stack<ListNode> stack = new Stack<>();
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        // 弹出指定元素
        while (n-- > 0) {
            stack.pop();
        }
        // 定位到待删除节点的前一个节点
        ListNode node = stack.peek();
        node.next = node.next.next; // 删除节点
        // 返回处理后的链表
        return dummy.next;
    }
}
