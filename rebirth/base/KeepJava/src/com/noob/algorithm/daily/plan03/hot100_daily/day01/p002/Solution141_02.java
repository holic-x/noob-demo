package com.noob.algorithm.daily.plan03.hot100_daily.day01.p002;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

/**
 * 🟢 141 环形链表I - https://leetcode.cn/problems/linked-list-cycle/description/
 */
public class Solution141_02 {
    /**
     * 思路分析：给定一个链表的头节点head，判断链表中是否有环
     * 快慢指针思路判断是否存在环：龟兔赛跑，同时出发，慢指针走1步、快指针走2步，如果快指针优先走出列表尾部则不存在环，如果相遇则说明存在环
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // 判断是否相遇，如果相遇则说明存在环(直接跳出循环返回校验结果)
            if (slow == fast) {
                return true;
            }
        }
        // 循环正常跳出，说明fast走到链表尾部了
        return false;
    }
}
