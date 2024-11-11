package com.noob.algorithm.dmsxl.leetcode.q160;

import com.noob.algorithm.dmsxl.baseStructure.ListNode;

/**
 * 160 相交链表
 */
public class Solution1 {
    // 双指针思路
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 边界条件判断
        if (headA == null || headB == null) {
            return null;
        }

        // 分别定义两个指针执行遍历操作：A->B、B->A链表
        ListNode pointerA = headA;
        ListNode pointerB = headB;
        while (pointerA != pointerB) {
            // A 链表遍历
            if (pointerA != null) {
                pointerA = pointerA.next;
            } else {
                // 如果遍历到A链表尾部，则切到B链表
                pointerA = headB;
            }

            // B 链表遍历
            if (pointerB != null) {
                pointerB = pointerB.next;
            } else {
                // 如果遍历到B链表尾部，则切到A链表
                pointerB = headA;
            }
        }
        return pointerA;
    }
}
