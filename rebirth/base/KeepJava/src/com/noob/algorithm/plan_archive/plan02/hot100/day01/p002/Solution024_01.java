package com.noob.algorithm.plan_archive.plan02.hot100.day01.p002;

import com.noob.algorithm.daily.base.ListNode;

/**
 * 🟡 024 两两交换链表中的节点
 */
public class Solution024_01 {

    /**
     * 思路分析：1->2->3->4(结合实际案例分析，交换涉及到4个节点)
     */
    public ListNode swapPairs(ListNode head) {
        // 构建虚拟头节点
        ListNode dummy = new ListNode(-1, head);
        // 定义遍历指针
        ListNode cur = dummy;
        // 遍历链表
        while (cur != null && cur.next != null && cur.next.next != null) {
            // 交换元素
            ListNode first = cur.next;
            ListNode second = cur.next.next;
            ListNode third = cur.next.next.next;
            cur.next = second;
            second.next = first;
            first.next = third;
            // 指针后移，指向下一个待处理位置
            cur = first;
        }
        // 返回交换处理后的链表
        return dummy.next;
    }

}
