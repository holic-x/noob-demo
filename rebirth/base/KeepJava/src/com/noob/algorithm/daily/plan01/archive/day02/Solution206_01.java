package com.noob.algorithm.daily.plan01.archive.day02;

import com.noob.algorithm.dmsxl.baseStructure.tree.ListNode;

/**
 * 🟢 206 反转链表
 */
public class Solution206_01 {

    // 方法1：遍历+头插
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }

        // 构建虚拟头节点
        ListNode dummy = null;
        // 构建遍历节点
        ListNode cur = head;
        // 遍历head链表，头插（入新链表）
        while (cur != null) {
            dummy = new ListNode(cur.val, dummy);
            cur = cur.next; // 指针后移
        }
        // 返回构建的新链表
        return dummy;
    }
}
