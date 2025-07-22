package com.noob.algorithm.daily.plan03.hot100_daily.day01.p002;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 🟢 160 相交链表 - https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/description/
 */
public class Solution160_02 {
    /**
     * 思路分析：返回两个链表的交点
     * 哈希法：将headA链表节点加入set集合，随后遍历headB
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        Set<ListNode> set = new HashSet<>();
        while (pA != null) {
            set.add(pA);
            pA = pA.next;
        }

        ListNode pB = headB;
        while (pB != null) {
            if (set.contains(pB)) {
                return pB;
            }
            pB = pB.next;
        }

        // 返回结果
        return null;
    }
}
