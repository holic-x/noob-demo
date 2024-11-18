package com.noob.algorithm.dmsxl.leetcode.q141;

import com.noob.algorithm.dmsxl.baseStructure.ListNode;

/**
 * 141 环形链表
 */
public class Solution1 {
    // 快慢指针思路：龟兔赛跑
    public boolean hasCycle(ListNode head) {
        // null 判断
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        // 当fast先走到终点（null）则跳出循环
        while (fast != null && fast.next != null) {
            // 指针后移(先走后判断，起点相同（如果先判断则逻辑错误了）)
            slow = slow.next; // 龟走1步
            fast = fast.next.next; // 兔子走两步
            // 如果这个过程中两个指针相遇，说明存在环
            if (slow == fast) { // 兔子追上龟（套圈）
                return true;
            }
        }
        return false; // fast访问到了链表末尾，说明无环
    }
}
