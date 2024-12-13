package com.noob.algorithm.plan01.day03;

import com.noob.algorithm.dmsxl.baseStructure.tree.ListNode;

/**
 * 🟡 141 环形链表I
 * https://leetcode.cn/problems/linked-list-cycle/description/
 */
public class Solution141_02 {

    /**
     * 思路：双指针思路（龟兔赛跑）
     */
    public boolean hasCycle(ListNode head) {
        // 特例判断
        if (head == null) {
            return false;
        }

        // 定义快慢指针（龟兔赛跑）
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) { // 如果不存在环则快指针会到达终点，如果存在环则一直转圈圈
            slow = slow.next; // 指针走1步
            fast = fast.next.next; // 指针走两步
            // 如果出现环，则快慢指针最终会相遇
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
