package com.noob.algorithm.plan01.day02;

import com.noob.algorithm.dmsxl.baseStructure.ListNode;

/**
 * 203 移除链表元素
 */
public class Solution203_01 {

    // 递归法
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }
}
