package com.noob.algorithm.daily.plan03.hot100_daily.day01.p002;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

/**
 * 🟢 160 相交链表 - https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/description/
 */
public class Solution160_01 {
    /**
     * 思路分析：返回两个链表的交点
     * 双指针遍历思路：定义两个指针分别按照A->B、B->A 的遍历顺序处理，两者相遇点为交点（交点存在则为存在相交，交点为null则不相交）
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2) {
            if (p1 == null) {
                p1 = headB;
            } else {
                p1 = p1.next;
            }

            if (p2 == null) {
                p2 = headA;
            } else {
                p2 = p2.next;
            }
        }

        // 返回结果
        return p1;
    }
}
