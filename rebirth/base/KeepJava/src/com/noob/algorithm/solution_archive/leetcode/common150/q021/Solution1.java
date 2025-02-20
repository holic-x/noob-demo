package com.noob.algorithm.solution_archive.leetcode.common150.q021;

import com.noob.algorithm.solution_archive.leetcode.common150.base.ListNode;

/**
 * 021 合并两个有序链表
 */
public class Solution1 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        // 定义虚拟链表头（构建新链表存储合并后的链表）
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy ; // 定义指针节点，指向合并后的链表

        // 定义链表指针
        ListNode p1 = l1;
        ListNode p2 = l2;

        // 遍历两个链表元素
        while(p1!=null && p2!=null){
            int val1 = p1.val;
            int val2 = p2.val;
            // 选择较小的元素优先插入
            if(val1<=val2){
                // 选择链表l1的当前指向元素插入
                cur.next = new ListNode(val1);
                p1 = p1.next; // 指针后移，指向下一个元素
                cur = cur.next; // 指针后移
            }else{
                // 选择链表l2的当前指向元素插入
                cur.next = new ListNode(val2);
                p2 = p2.next; // 指针后移，指向下一个元素
                cur = cur.next; // 指针后移
            }
        }

        // 当两个链表中其中一个链表遍历到尾节点，则需将剩余节点拼接到合并后的链表尾部即可
        if(p1!=null){
            cur.next = p1;
        }
        if(p2!=null){
            cur.next = p2;
        }

        // 返回合并后的链表
        return dummy.next;
    }
}
