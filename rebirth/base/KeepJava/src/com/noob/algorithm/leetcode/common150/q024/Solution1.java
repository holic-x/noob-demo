package com.noob.algorithm.leetcode.common150.q024;

import com.noob.algorithm.leetcode.common150.base.ListNode;

/**
 * 024 两两交换链表元素
 */
public class Solution1 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0, head); // 头插 在head头部补一个虚拟头节点
        ListNode cur = dummy; // 定义指针指向dummy

        while (cur.next != null && cur.next.next != null) {
            // 遍历链表依次进行交换
            ListNode first = cur.next;
            ListNode sec = cur.next.next;
            ListNode third = cur.next.next.next;
            // 交换元素
            cur.next = sec;
            sec.next = first;
            first.next = third;
            // 交换完成，cur指向下一个交换节点
            cur = first; // cur 的下个交换节点应为交换前的first
        }

        // 返回结果
        return dummy.next;
    }
}
