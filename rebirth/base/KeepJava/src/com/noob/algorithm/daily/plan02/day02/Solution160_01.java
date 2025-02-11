package com.noob.algorithm.daily.plan02.day02;

import com.noob.algorithm.daily.base.ListNode;

/**
 * 🟢 160 相交链表 - https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/description/
 */
public class Solution160_01 {
    /**
     * 思路分析：模拟法，用两个指针分别以A->B、B->A的顺序遍历链表，两者的交点有两种情况：
     * ① 公共交点：存在则为公共交点
     * ② 尾节点null
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 定义两个遍历指针:p1(A->B) p2(B->A)
        ListNode p1 = headA, p2 = headB;
        // 遍历两个链表
        while (p1 != p2) { // 两指针相交的遍历结束
            if (p1 != null) {
                p1 = p1.next;
            } else {
                p1 = headB; // 切换到链表B头节点
            }

            if (p2 != null) {
                p2 = p2.next;
            } else {
                p2 = headA;
            }
        }
        // 返回交点
        return p1; // 最终跳出循环的节点要么是公共交点，要么是遍历到了尾节点（null）
    }
}
