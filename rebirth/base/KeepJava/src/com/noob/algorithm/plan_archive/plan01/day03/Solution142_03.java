package com.noob.algorithm.plan_archive.plan01.day03;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.ListNode;

/**
 * 🟡 142 环形链表II
 */
public class Solution142_03 {

    /**
     * 思路：双指针思路
     * 龟兔赛跑，判断是否存在环，如果存在环则继续寻找环的入口
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        // 定义快慢指针
        ListNode slow = head, fast = head;
        // 判断是否存在环，如果存在环则从相遇点跳出
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next; // 慢指针走1步
            fast = fast.next.next; // 快指针走2步
            if (slow == fast) {
                // 如果快慢指针相遇则存在环
                hasCycle = true;
                break; // 相遇则跳出
            }
        }

        // 判断是否存在环，如果存在则继续寻找环的入口
        if (!hasCycle) {
            return null; // 不存在环，直接退出
        }

        // 存在环，继续寻找环的入口（定义指针cur从起点出发，与相遇点slow同步前进，两者相遇的点即为环的入口）
        ListNode cur = head; // 此处也可以复用fast指针空间，为了区别作用定义了cur指针
        while (cur != slow) {
            cur = cur.next;
            slow = slow.next;
        }
        // 返回环的起点（入环口）
        return cur;
    }
}
