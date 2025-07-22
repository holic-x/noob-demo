package com.noob.algorithm.daily.plan03.hot100_daily.day01.p002;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

/**
 * 🟡 142 环形链表II - https://leetcode.cn/problems/linked-list-cycle-ii/description/
 */
public class Solution142_02 {
    /**
     * 思路分析：给定链表head，如果存在环则返回入环的第1个节点，如果链表无环则返回null
     * 处理：快慢指针思路，先判断是否存在环，如果存在环则记录slow指针。随后定义指针从起点和slow同时同步出发，两者相遇点即为入环点
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // 判断是否相遇
            if (slow == fast) {
                hasCycle = true;
                break; // 跳出循环
            }
        }

        // 校验是否存在环
        if (!hasCycle) {
            return null;
        }

        // 存在环，继续寻找入环起点
        ListNode cur = head;
        while (cur != slow) {
            cur = cur.next;
            slow = slow.next;
        }
        return cur;
    }
}
