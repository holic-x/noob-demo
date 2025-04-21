package com.noob.algorithm.daily.plan03.hot100_random.day01.p002;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

import java.util.HashSet;

/**
 * 🟢 141 环形链表I - https://leetcode.cn/problems/linked-list-cycle/description/
 */
public class Solution141_02 {
    /**
     * 思路分析：给定一个链表的头节点head，判断链表中是否有环
     * 快慢指针
     */
    public boolean hasCycle(ListNode head) {
        // 定义快慢指针初始化起点相同
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // 快指针如果先到达终点则说明不存在环，如果快指针和慢指针相遇则说明存在环
            if (slow == fast) {
                return true;
            }
        }

        // 不存在环
        return false;
    }
}
