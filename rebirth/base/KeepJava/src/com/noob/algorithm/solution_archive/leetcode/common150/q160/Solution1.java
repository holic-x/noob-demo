package com.noob.algorithm.solution_archive.leetcode.common150.q160;

import com.noob.algorithm.solution_archive.leetcode.hot100.base.ListNode;

/**
 * 160 相交链表
 */
public class Solution1 {

    /**
     * 遍历链表
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 定义指针用作遍历
        ListNode pa = headA;
        ListNode pb = headB;

        // 当指针不相遇则继续遍历
        while (pa != pb) {
            // 如果A链表遍历完则指向B
            if (pa == null) {
                pa = headB;
            } else {
                pa = pa.next;
            }
            // 如果B遍历完则指向A
            if (pb == null) {
                pb = headA;
            } else {
                pb = pb.next;
            }
        }

        // 返回交点
        return pa;
    }
}
