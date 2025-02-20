package com.noob.algorithm.leetcode.common150.q141;

import com.noob.algorithm.leetcode.common150.base.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 141 环形链表
 */
public class Solution2 {
    /**
     * 判断链表中是否存在环
     * 思路：快慢指针
     * 1.如果链表不存在环，则必然会遍历到链表尾部（也就是说快指针到达链表尾部的时候，就会跳出链表遍历了）
     * 2.如果链表存在环，则快慢指针必然会相遇（因为存在环，快慢指针必然会在圈子内转圈圈，最终在某个时刻相遇）
     */
    public boolean hasCycle(ListNode head) {
        // 定义快慢指针：如果链表中存在环则快慢指针最终会相遇，如果不存在环则均能遍历到尾部
        ListNode slow = head;
        ListNode fast = head;
        // 遍历元素
        while (fast != null && fast.next != null) { // 如果快指针遍历到链表尾部则循环结束
            // 快慢指针继续往前
            slow = slow.next; // 慢指针走1步
            fast = fast.next.next; // 快指针走2步
            // 判断如果指针相遇，则说明存在环
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
