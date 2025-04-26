package com.noob.algorithm.daily.plan03.hot100_random.day01.p002;


import com.noob.algorithm.plan_archive.baseStructure.ListNode;

import java.util.Stack;

/**
 * 🟢 206 反转链表 - https://leetcode.cn/problems/reverse-linked-list/
 */
public class Solution206_02 {

    /**
     * 思路分析：
     * 栈辅助
     */
    public ListNode reverseList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        ListNode dummy = new ListNode(-1);
        ListNode pt = dummy;

        // 处理栈元素
        while (!stack.isEmpty()) {
            ListNode node = stack.pop();
            pt.next = node;
            pt = pt.next;
        }
        pt.next = null; // handle cycle error

        // 返回结果
        return dummy.next;
    }
}
