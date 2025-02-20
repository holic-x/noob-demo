
package com.noob.algorithm.plan_archive.plan02.hot100.day01.p002;

import com.noob.algorithm.daily.base.ListNode;

/**
 * 🟢 141 环形链表I - https://leetcode.cn/problems/linked-list-cycle/description/
 */
public class Solution141_02 {
    /**
     * 思路分析：双指针（快慢指针（龟兔赛跑概念））
     */
    public boolean hasCycle(ListNode head) {
        // 初始化双指针
        ListNode slow = head, fast = head;
        // 遍历链表，如果fast指针先走到尾部则说明不存在环，如果fast与slow相遇则存在环
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        // 循环正常结束，说明fast指针遍历到链表尾部，即不存在环
        return false;
    }
}
