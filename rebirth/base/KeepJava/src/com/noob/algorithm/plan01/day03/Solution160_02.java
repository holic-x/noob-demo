package com.noob.algorithm.plan01.day03;

import com.noob.algorithm.dmsxl.baseStructure.tree.ListNode;

/**
 * 🟢 160 链表相交
 */
public class Solution160_02 {

    // 思路：双指针法
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 定义双指针分别遍历链表headA、headB
        ListNode pa = headA, pb = headB;
        while (pa != pb) {
            // pa 指针遍历移动，如果headA遍历完成就跳到headB
            if (pa == null) {
                pa = headB;
            } else {
                pa = pa.next;
            }

            // pb 指针遍历移动，如果headB遍历完成就跳到headA
            if (pb == null) {
                pb = headA;
            } else {
                pb = pb.next;
            }
        }
        // 指针相遇最终返回交点或者是null
        return pa;
    }
}
