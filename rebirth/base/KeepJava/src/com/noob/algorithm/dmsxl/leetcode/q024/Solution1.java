package com.noob.algorithm.dmsxl.leetcode.q024;

import com.noob.algorithm.dmsxl.baseStructure.ListNode;

/**
 * 024 两两交换链表的节点
 */
public class Solution1 {
    // 迭代法
    public ListNode swapPairs(ListNode head) {
        // 定义虚拟头节点构建链表
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy; // 遍历指针，指向dummy

        // 遍历
        while (cur.next != null && cur.next.next != null) {
            // 记录节点
            ListNode first = cur.next;
            ListNode second = first.next;
            ListNode third = second.next;
            // 交换节点
            cur.next = second;
            second.next = first;
            first.next = third;
            // 交换完成，cur指针后移，指向下一个待交换的位置
            cur = first; // 结合图示理解，此时应该指向原来的first
        }

        // 返回链表
        return dummy.next;
    }
}
