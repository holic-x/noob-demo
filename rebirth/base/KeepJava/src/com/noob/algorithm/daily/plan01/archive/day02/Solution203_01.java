package com.noob.algorithm.daily.plan01.archive.day02;

import com.noob.algorithm.dmsxl.baseStructure.tree.ListNode;

/**
 * 🟢 203 移除链表元素
 * https://leetcode.cn/problems/remove-linked-list-elements/submissions/578999672/
 */
public class Solution203_01 {

    // 递归法
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }
}
