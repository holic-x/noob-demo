package com.noob.algorithm.dmsxl.leetcode.q019;

import com.noob.algorithm.dmsxl.baseStructure.ListNode;

import java.util.*;

/**
 * 019 删除链表的倒数第N个节点
 */
public class Solution2 {

    // 迭代：辅助列表（倒数第N个节点，对于队列而言是第L-N+1，注意虚拟头节点的影响）
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 辅助队列(借助列表集合)
        List<ListNode> queue = new ArrayList<>();

        // 虚拟头节点
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;

        // 遍历链表
        while (cur != null) {
            queue.add(cur);
            cur = cur.next; // 指针后移
        }

        // 取到倒数第N-1
        ListNode prev = queue.get(queue.size() - n -1); // 因为多加了个虚拟头节点，因此此处要注意取值
        prev.next = prev.next.next; // 执行删除

        // 返回链表
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;

        Solution2 solution2 = new Solution2();
        solution2.removeNthFromEnd(node1,2);

    }

}
