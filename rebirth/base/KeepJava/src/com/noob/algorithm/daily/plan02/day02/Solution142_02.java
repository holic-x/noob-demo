
package com.noob.algorithm.daily.plan02.day02;

import com.noob.algorithm.daily.base.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 142 环形链表II - https://leetcode.cn/problems/linked-list-cycle-ii/description/
 */
public class Solution142_02 {
    /**
     * 思路分析：快慢指针
     * ① 先判断是否存在环（141 思路），得到slow
     * ② 让head和slow同时同步出发，两者再次相遇得到环入口
     */
    public ListNode detectCycle(ListNode head) {
        // ① 定义快慢指针判断是否存在环
        ListNode slow = head, fast = head;
        // 定义环标识
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                break; // 跳出循环
            }
        }
        // 根据环标识校验
        if (!hasCycle) {
            return null; // 不存在环
        }

        // 存在环，进一步找出环入口
        ListNode p = head;
        while (p != slow) {
            p = p.next;
            slow = slow.next;
        }
        // 返回环入口
        return slow;
    }
}
