package com.noob.algorithm.plan_archive.plan01.day24;

import com.noob.algorithm.daily.base.ListNode;

/**
 * 🟢 160 相交链表 - https://leetcode.cn/problems/intersection-of-two-linked-lists/description/
 */
public class Solution160_01 {

    /**
     * 思路：双指针遍历链表元素（基于A+B=B+A的思路）
     * 则两者相遇的情况有两种：要么end 要么相交
     * - pA、pB都遍历到链表尾部，两个指针都指向null
     * - pA、pB存在交点，在交点处相遇
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 定义两个指针，分别从headA、headB出发
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            // 处理pA
            if (pA == null) {
                pA = headB; // pA 遍历到headA尾部则切换到headB
            } else {
                pA = pA.next;
            }

            // 处理pB
            if (pB == null) {
                pB = headA; // pB 遍历到headB尾部则切换到headA
            } else {
                pB = pB.next;
            }
        }

        // 返回结果
        return pA;
    }

}
