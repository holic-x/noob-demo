package com.noob.algorithm.daily.plan02.day01;

import com.noob.algorithm.daily.base.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 160 相交链表 - https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/description/
 */
public class Solution160_02 {
    /**
     * 思路分析：哈希法
     * ① 先遍历headA链表加入集合
     * ② 随后遍历headB链表，看节点是否存在与集合
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        List<ListNode> list = new ArrayList<>();
        // 遍历链表A
        ListNode p1 = headA;
        while (p1 != null) {
            list.add(p1);
            p1 = p1.next;
        }
        // 遍历链表B
        ListNode p2 = headB;
        while (p2 != null) {
            if (list.contains(p2)) {
                return p2;
            }
            p2 = p2.next;
        }
        // 不存在公共交点
        return null;
    }
}
