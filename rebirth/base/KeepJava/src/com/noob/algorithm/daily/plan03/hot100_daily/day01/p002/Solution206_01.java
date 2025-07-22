package com.noob.algorithm.daily.plan03.hot100_daily.day01.p002;


import com.noob.algorithm.plan_archive.baseStructure.ListNode;

import java.util.Stack;

/**
 * 🟢 206 反转链表 - https://leetcode.cn/problems/reverse-linked-list/
 */
public class Solution206_01 {

    /**
     * 思路分析：反转链表（遍历辅助，构建新链表）
     */
    public ListNode reverseList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode p = head;
        while (p != null) {
            stack.push(p);
            p = p.next;
        }

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (!stack.isEmpty()) {
            cur.next = stack.pop();
            cur = cur.next;
        }
        cur.next = null; // handle Error - Found cycle in the ListNode

        // 返回结果
        return dummy.next;
    }
}
