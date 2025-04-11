package com.noob.algorithm.daily.codeTop;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.ListNode;

/**
 * 🟢 024 两两交换链表中的节点 - https://leetcode.cn/problems/swap-nodes-in-pairs/description/
 */
public class Solution024_01 {

    public ListNode swapPairs(ListNode head) {
        // 构建虚拟头节点
        ListNode dummy = new ListNode(-1, head);
        // 定义指针遍历链表
        ListNode cur = dummy;
        // 处理节点交换
        while (cur.next != null && cur.next.next != null) {
            // 记录交换节点
            ListNode first = cur.next;
            ListNode sec = cur.next.next;
            ListNode third = cur.next.next.next;
            // 两两交换节点
            cur.next = sec;
            sec.next = first;
            first.next = third;
            // 指针移动
            cur = first; // 指向下一个待交换位置
        }
        // 返回
        return dummy.next;
    }
}
