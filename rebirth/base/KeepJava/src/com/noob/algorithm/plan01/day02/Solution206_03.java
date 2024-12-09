package com.noob.algorithm.plan01.day02;

import com.noob.algorithm.dmsxl.baseStructure.ListNode;

import java.util.Stack;

/**
 * 🟢 206 反转链表
 */
public class Solution206_03 {

    // 方法3：滚动指针变量
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }

        // 构建虚拟头节点
        ListNode prev = null; // prev指向的是cur.prev(因为是反转概念，因此初始化为null，随后不断补充元素)
        ListNode cur = head; // cur指向的是prev.next

        // 遍历元素，依次反转两个节点，遍历指针滚动后移
        while (cur != null) {
            // 反转元素(prev->cur->nxt  =>  反转：cur->prev  nxt,滚动指针prev=cur, cur = nxt)
            ListNode nxt = cur.next;
            cur.next = prev;
            // 滚动指针变量
            prev = cur;
            cur = nxt;
        }

        // 输出反转后的链表
        return prev;
    }
}
