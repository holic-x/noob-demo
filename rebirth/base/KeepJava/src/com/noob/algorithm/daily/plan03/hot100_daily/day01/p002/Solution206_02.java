package com.noob.algorithm.daily.plan03.hot100_daily.day01.p002;


import com.noob.algorithm.plan_archive.baseStructure.ListNode;

/**
 * 🟢 206 反转链表 - https://leetcode.cn/problems/reverse-linked-list/
 */
public class Solution206_02 {

    /**
     * 思路分析：反转链表(原地反转)
     */
    public ListNode reverseList(ListNode head) {
        ListNode tail = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = tail;

            // 更新节点
            tail = cur;
            cur = next;
        }
        return tail;
    }
}
