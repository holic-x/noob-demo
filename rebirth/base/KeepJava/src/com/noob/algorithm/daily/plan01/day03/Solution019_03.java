package com.noob.algorithm.daily.plan01.day03;

import com.noob.algorithm.dmsxl.baseStructure.tree.ListNode;

/**
 * 🟡019 删除链表的倒数第N个节点
 */
public class Solution019_03 {
    /**
     * 思路：快慢指针
     * 快指针先走N步，然后快慢指针同时出发，当快指针走到终点，此时慢指针指向位置就是删除位置
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 构建虚拟头节点
        ListNode dummy = new ListNode(-1, head);
        ListNode slow = dummy, fast = head;
        // 快指针出发先走N步
        while (n-- > 0) {
            fast = fast.next;
        }
        // 快慢指针同时出发（快指针和慢指针始终相差N+1）
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // 慢指针位置为删除位置
        slow.next = slow.next.next;
        // 返回结果
        return dummy.next;
    }

}
