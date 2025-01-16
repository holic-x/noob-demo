package com.noob.algorithm.daily.plan01.archive.day24;

import com.noob.algorithm.daily.base.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 160 相交链表 - https://leetcode.cn/problems/intersection-of-two-linked-lists/description/
 */
public class Solution160_02 {

    /**
     * 思路：哈希思路
     * - 遍历headA将元素加入集合，随后遍历headB并校验在集合中是否已经存在元素
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 构建哈希表存储headA元素
        List<ListNode> list = new ArrayList<>();

        // ① 遍历headA并载入集合
        ListNode pA = headA;
        while (pA != null) {
            list.add(pA);
            pA = pA.next;
        }

        // ② 遍历headB并校验集合中是否存在元素
        ListNode pB = headB;
        while (pB != null) {
            if (list.contains(pB)) {
                return pB;
            }
            pB = pB.next;
        }

        // 不存在交点
        return null;
    }

}
