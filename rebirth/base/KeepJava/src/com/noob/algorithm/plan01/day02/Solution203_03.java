package com.noob.algorithm.plan01.day02;

import com.noob.algorithm.dmsxl.baseStructure.ListNode;

/**
 * 203 移除链表元素
 */
public class Solution203_03 {

    // 迭代法：迭代的过程中遍历元素构建新链表，跳过满足Node.val == val的节点，最终返回构建好的新链表
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        ListNode pointer = dummy; // 用于构建dummy新链表的指针
        ListNode cur = head; // 用于遍历head链表的指针
        while (cur != null) {
            // 将不等于val的节点加入到新链表
            if (cur.val != val) {
                pointer.next = new ListNode(cur.val); // 加入新节点
                pointer = pointer.next; // 指针移动
            }
            cur = cur.next; // 指针后移（继续遍历下个节点）
        }
        // 返回结果
        return dummy.next;
    }
}
