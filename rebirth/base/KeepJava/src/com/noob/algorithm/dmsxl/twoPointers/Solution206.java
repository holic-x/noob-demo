package com.noob.algorithm.dmsxl.twoPointers;

import com.noob.algorithm.dmsxl.baseStructure.tree.ListNode;

/**
 * 206 反转单链表
 */
public class Solution206 {

    /**
     * 链表反转：pre、cur、nxt
     * 核心：将cur.next指向pre，随后pre、cur指针后移，在此需注意先记录nxt（cur.next）
     */
    public ListNode reverseList(ListNode head) {
        // 定义虚拟头节点
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy.next; // cur 指向遍历指针
        ListNode pre = null; // pre 作为尾节点（初始化时）
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = pre; // 将cur的next指针指向前一个节点
            // 双指针继续后移
            pre = cur;
            cur = nxt;
        }
        // 最终pre指向反转链表
        return pre;
    }

}
