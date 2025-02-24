package com.noob.algorithm.solution_archive.dmsxl.leetcode.q142;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.ListNode;

/**
 * 142 环形链表II
 */
public class Solution1 {

    // 快慢指针思路：龟兔赛跑（先判断是否存在环，如果存在环则继续下一步寻找入口）
    public ListNode detectCycle(ListNode head) {
        // null 判断
        if (head == null) {
            return null;
        }
        // 龟兔赛跑起点相同
        ListNode slow = head;
        ListNode fast = head;
        // 找到指针相遇的点
        while (true) {
            // 当fast先走到终点（null）则跳出循环
            if (fast == null || fast.next == null) {
                return null; // 如果fast指针可以遍历到尾部说明不存在环，直接return null
            }
            // 指针后移
            slow = slow.next;
            fast = fast.next.next;
            // 如果这个过程中两个指针相遇，说明存在环
            if (slow == fast) {
                break; // 如果存在环，则跳出当前循环
            }
        }

        // 如果执行到此步骤，说明链表存在环，则进一步寻找环的入口
        fast = head; // 让fast从头开始走
        while (slow != fast) { // 既然明确存在环，则让fast从头节点出发，slow从当前位置出发，一起走总会相遇，相遇点即为环入口
            slow = slow.next;
            fast = fast.next;
        }
        return fast; // 此时两者相遇即入口，返回slow和fast均可
    }
}
