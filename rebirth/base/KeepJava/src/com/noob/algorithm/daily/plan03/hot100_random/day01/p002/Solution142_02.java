package com.noob.algorithm.daily.plan03.hot100_random.day01.p002;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

import java.util.HashSet;

/**
 * 🟡 142 环形链表II - https://leetcode.cn/problems/linked-list-cycle-ii/description/
 */
public class Solution142_02 {
    /**
     * 思路分析：给定链表head，如果存在环则返回入环的第1个节点，如果链表无环则返回null
     * 快慢指针：先基于快慢指针思路判断链表是否存在环，如果存在则进一步寻找入口
     */
    public ListNode detectCycle(ListNode head) {

        // 定义环标识
        boolean hasCycle = false;

        // 定义快慢指针：起点相同，步长不同
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // 判断是否相遇
            if (slow == fast) {
                // 相遇则说明存在环
                hasCycle = true;
                break; // 跳出循环
            }
        }

        // 判断环标识
        if (!hasCycle) {
            return null; // 不存在环
        }

        // 存在环，进一步寻找入环起点
        ListNode cur = head;
        while (cur != slow) {
            slow = slow.next;
            cur = cur.next;
        }

        // 返回入环起点
        return cur;

    }
}
