package com.noob.algorithm.hot100.q141;

import com.noob.algorithm.hot100.base.ListNode;

/**
 * 141 环形链表
 * 快慢指针
 */
public class Solution2 {

    /**
     * 定义快慢指针进行环形判断：
     * 1.如果链表不存在环，则必然会遍历到链表尾部（也就是说快指针到达链表尾部的时候，就会跳出链表遍历了）
     * 2.如果链表存在环，则快慢指针必然会相遇（因为存在环，快慢指针必然会在圈子内转圈圈，最终在某个时刻相遇）
     */
    public boolean hasCycle(ListNode head) {
        // 定义快慢指针（两者的起点相同）
        ListNode slow = head;
        ListNode fast = head;

        // 迭代链表，看指针是否会相遇
        while (fast != null && fast.next != null) {
            // 此处必须先走后判断（否则起始值一样的情况下slow==fast始终成立）
            slow = slow.next; // 指针继续后移
            fast = fast.next.next; // 指针继续后移
            if (slow == fast) {
                return true; // 快慢指针相遇，则说明存在环
            }
        }
        // 跳出循环，说明快指针遍历到链表尾部了，即不存在环
        return false;
    }

}
