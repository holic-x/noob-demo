package com.noob.algorithm.daily.codeTop;

import com.noob.algorithm.daily.base.ListNode;

/**
 * 🟢021 合并两个有序链表
 */
public class Solution021_01 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 构建虚拟头节点
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy; // 构建dummy新链表指针

        // 分别定义两个链表指针
        ListNode p1 = l1;
        ListNode p2 = l2;
        // 同时遍历两个链表，比较链表节点值并封装新链表
        while (p1 != null && p2 != null) {
            // 选择较小的节点值封装
            if (p1.val <= p2.val) {
                // 选择l1链表的节点
                cur.next = new ListNode(p1.val);
                cur = cur.next;
                // p1指针后移
                p1 = p1.next;
            } else {
                // 选择l2链表的节点
                cur.next = new ListNode(p2.val);
                cur = cur.next;
                // p2指针后移
                p2 = p2.next;
            }
        }

        // 校验剩余的链表（l1、l2本身有序，直接进行拼接）
        if (p1 != null) {
            cur.next = p1;
        }
        if (p2 != null) {
            cur.next = p2;
        }

        // 返回构建的新链表
        return dummy.next;
    }
}
