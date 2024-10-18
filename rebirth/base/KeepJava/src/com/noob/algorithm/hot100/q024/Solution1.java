package com.noob.algorithm.hot100.q024;

import com.noob.algorithm.hot100.q160.ListNode;

/**
 * 24 两两交换链表的节点
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）
 */
public class Solution1 {

    /**
     * 此处的两两交换概念，类似于两个为一组进行交换，交换完成进入下一组，依次类推
     */
    public ListNode swapPairs(ListNode head) {
        // 定义虚拟头节点
        ListNode dummy = new ListNode(0,head); // 初始化接入head链表构成一个新链表

        // 循环遍历链表，两个为一组进行交换
        ListNode cur = dummy;

        while (cur.next != null && cur.next.next != null) {
            ListNode first = cur.next;
            ListNode second = first.next; //  cur.next.next
            ListNode third = second.next; //  cur.next.next.next

            // 变更指针关系(交换元素)
            cur.next = second;
            second.next = first;
            first.next = third;

            // 指针后移，准备下一轮交换(需注意此处cur指向的要遍历的下一个节点应为first,而非cur.next，因为cur.next在上面改变了，下一个节点应该是对应first)
            cur = first;
        }
        // 返回结果
        return dummy.next;
    }
}
