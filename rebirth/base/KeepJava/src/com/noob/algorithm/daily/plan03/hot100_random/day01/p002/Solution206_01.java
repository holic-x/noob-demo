package com.noob.algorithm.daily.plan03.hot100_random.day01.p002;


import com.noob.algorithm.plan_archive.baseStructure.ListNode;

/**
 * 🟢 206 反转链表 - https://leetcode.cn/problems/reverse-linked-list/
 */
public class Solution206_01 {

    /**
     * 思路分析：
     * 反转链表
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        while(cur!=null){
            ListNode nxt = cur.next;
            // 调整指针
            cur.next = pre;
            // 更新指针位置
            pre = cur;
            cur = nxt;
        }

        return pre;
    }
}
