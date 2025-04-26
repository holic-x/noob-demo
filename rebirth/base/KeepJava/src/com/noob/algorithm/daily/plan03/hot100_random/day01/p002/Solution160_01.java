package com.noob.algorithm.daily.plan03.hot100_random.day01.p002;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

/**
 * 🟢 160 相交链表 - https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/description/
 */
public class Solution160_01 {
    /**
     * 思路分析：
     * 判断链表是否相交：返回两个单链表相交的起点，如果没有则返回null。基于双指针思路构建
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 分别定义两个指针，分别从A链表、B链表出发
        ListNode pa = headA, pb = headB;

        // 遍历链表:pa:headA->headB   pb:headB->headA  如果两者相遇要么是交点 要么是终点
        while (pa != pb) {
            if (pa != null) {
                pa = pa.next;
            } else {
                pa = headB;
            }

            if (pb != null) {
                pb = pb.next;
            } else {
                pb = headA;
            }
        }

        // 返回交点
        return pa;
    }
}
