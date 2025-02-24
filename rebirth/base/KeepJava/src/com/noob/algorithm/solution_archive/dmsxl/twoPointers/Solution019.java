package com.noob.algorithm.solution_archive.dmsxl.twoPointers;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.ListNode;

/**
 * 019 删除链表的倒数第N个节点
 */
public class Solution019 {
    /**
     * 双指针：定义快慢指针
     * 1.快指针从head开始，让快指针先走n步   慢指针从起点（dummy）开始
     * 2.当快指针走了n步之后，双指针同时出发，当快指针走到链表尾部的时候此时慢指针恰好就走到倒数第n个节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 构建虚拟头节点
        ListNode dummy = new ListNode(-1, head);
        // 定义双指针
        ListNode slow = dummy, fast = head; // slow初始指向dummy（是为了拿到待删除节点的前驱节点），fast初始指向head
        // 1.快指针先走n步
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        // 2.双指针再一起出发，当快指针遍历到链表尾部，此时慢指针指向len-n（倒数第n的位置）的前驱节点位置
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // 删除指定节点
        slow.next = slow.next.next;
        // 返回
        return dummy.next;
    }
}
