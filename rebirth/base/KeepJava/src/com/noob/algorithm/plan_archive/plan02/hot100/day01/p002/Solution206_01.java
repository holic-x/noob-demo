package com.noob.algorithm.plan_archive.plan02.hot100.day01.p002;


import com.noob.algorithm.daily.base.ListNode;

/**
 * 🟢 206 反转链表 - https://leetcode.cn/problems/reverse-linked-list/
 */
public class Solution206_01 {

    /**
     * 思路分析：迭代法（构建虚拟头节点，依次反转每个节点指向）
     */
    public ListNode reverseList(ListNode head) {
        // 构建链表遍历指针
        ListNode cur = head;
        ListNode pre = null;
        // 遍历链表，反转
        while (cur != null) {
            // 调整pre、cur、nxt三个节点的关系
            ListNode nxt = cur.next;
            cur.next = pre;
            // 更新指针
            pre = cur;
            cur = nxt;
        }
        // 最终pre指向反转后的链表
        return pre;
    }
}
