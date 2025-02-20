package com.noob.algorithm.plan_archive.plan01.day23;

import com.noob.algorithm.daily.base.ListNode;

/**
 * 🟢 141 环形链表
 */
public class Solution141_02 {

    /**
     * 思路：快慢指针（快慢指针同时出发，如果相遇则说明存在环，如果快指针先到达终点则链表不存在环）
     */
    public boolean hasCycle(ListNode head) {
        // 定义快慢指针
        ListNode slow = head, fast = head;

        // 遍历元素
        while (fast != null && fast.next != null) {
            slow = slow.next; // 慢指针走1步
            fast = fast.next.next; // 快指针走2步
            if (slow == fast) {
                return true;
            }
        }

        // 快指针遍历到链表末尾，不存在环
        return false;
    }
}
