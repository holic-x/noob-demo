package com.noob.algorithm.dmsxl.leetcode.q203;

import com.noob.algorithm.dmsxl.baseStructure.ListNode;

/**
 * 203 移除链表元素
 */
public class Solution3 {
    /**
     * 递归
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }
}
