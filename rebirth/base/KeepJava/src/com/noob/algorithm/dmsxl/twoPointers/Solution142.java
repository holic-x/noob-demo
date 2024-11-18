package com.noob.algorithm.dmsxl.twoPointers;

import com.noob.algorithm.dmsxl.baseStructure.ListNode;

/**
 * 142 环形链表II（判断是否存在环，判断环入口）
 */
public class Solution142 {

    /**
     * 1.判断是否存在环（龟兔赛跑）：slow、fast起点相同，slow走一步，fast走两步
     * 2.如果存在环则获取环入口，让fast从起点、slow从当前位置一起出发，相遇位置即环入口
     */
    public ListNode detectCycle(ListNode head) {
        // head为空判断
        if (head == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;
        while (fast != null && fast.next != null) { // fast指针先遍历到链表尾部则循环结束
            slow = slow.next; // slow指针走1步
            fast = fast.next.next; // fast指针走2步
            // 判断是否相遇
            if (slow == fast) {
                hasCycle = true; // 存在环
                break; // 跳出当前循环，需要保留slow、fast当前位置
            }
        }

        // 判断是否存在环
        if (!hasCycle) {
            return null; // 不存在环直接返回
        }

        // 存在环：fast从起点、slow从当前位置继续同时同步出发，两者再次相遇即为入环口
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        // 返回入环口
        return slow;
    }

}
