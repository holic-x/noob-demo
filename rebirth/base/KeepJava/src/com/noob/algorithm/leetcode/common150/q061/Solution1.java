package com.noob.algorithm.leetcode.common150.q061;

import com.noob.algorithm.leetcode.common150.base.ListNode;
import org.w3c.dom.Node;

/**
 * 061 循环链表切节点
 */
public class Solution1 {

    // todo 切割链表待确认
    public ListNode rotateRight(ListNode head, int k) {
        // 定义指针遍历链表，计算链表长度
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;

        int len = 1;
        while (cur.next != null) {
            len++;
            cur = cur.next;
        }

        // 计算移动步数
        k = k % len;

        // 再次遍历链表，切割链表
        ListNode point = head;
        for (int i = 0; i < len - k - 1; i++) {
            point = point.next;
        }
        // 遍历到K-1位置，当前point即为切割位置(A,B)
        ListNode nxt = point.next; // 记录B链表

        ListNode pn = nxt;
        for (int i = 0; i < k - 1; i++) {
            pn = pn.next;
        }
        point.next = null; // A 链表位置指向尾节点
        pn.next = head;
        return nxt;
    }

}
