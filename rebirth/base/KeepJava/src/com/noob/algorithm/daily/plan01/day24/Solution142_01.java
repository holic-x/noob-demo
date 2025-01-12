package com.noob.algorithm.daily.plan01.day24;

import com.noob.algorithm.daily.base.ListNode;

/**
 * 🟡 142 环形链表II - https://leetcode.cn/problems/linked-list-cycle-ii/
 */
public class Solution142_01 {

    /**
     * 思路：快慢指针
     */
    public ListNode detectCycle(ListNode head) {

        // 定义快慢指针判断是否存在环
        ListNode slow = head, fast = head;

        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // 如果slow与fast相遇则说明存在环，跳出循环并记录环标记
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        // 校验是否存在环，不存在则返回空节点
        if (!hasCycle) {
            return null;
        }

        // 如果存在环，则定义一个指针从head头节点开始和当前的slow同步出发,两者再次相遇则为环入口
        ListNode cur = head;
        while (cur != slow) {
            cur = cur.next;
            slow = slow.next;
        }

        // 返回环入口
        return cur;

    }
}
