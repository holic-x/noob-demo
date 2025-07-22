package com.noob.algorithm.daily.plan03.hot100_daily.day01.p002;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

/**
 * 🟡 024 两两交换链表中的节点 - https://leetcode.cn/problems/swap-nodes-in-pairs/description/
 */
public class Solution024_01 {

    /**
     * 思路分析：
     */
    public ListNode swapPairs(ListNode head) {
        // 构建虚拟头节点
        ListNode dummy = new ListNode(-1, head);

        // 定义遍历指针
        ListNode p = dummy;
        while (p.next != null && p.next.next != null) {
            ListNode first = p.next;
            ListNode sec = p.next.next;
            ListNode third = p.next.next.next;
            // 两两交换节点(处理节点顺序，从左到右)
            p.next = sec;
            sec.next = first;
            first.next = third;

            // 继续下个节点处理
            p = first; // 2个节点处理完成，p指针移动
        }

        // 返回处理好的链表数据
        return dummy.next;
    }

}
