package com.noob.algorithm.daily.plan03.hot100_daily.day01.p002;


import com.noob.algorithm.plan_archive.baseStructure.ListNode;

/**
 * 🟢 206 反转链表 - https://leetcode.cn/problems/reverse-linked-list/
 */
public class Solution206_03 {

    /**
     * 思路分析：反转链表(头插)
     */
    public ListNode reverseList(ListNode head) {
        ListNode dummy = null;
        ListNode p = head;
        while (p != null) {
            dummy = new ListNode(p.val, dummy);
            p = p.next;
        }
        // 返回构建结果
        return dummy;
    }
}
