package com.noob.algorithm.daily.codeTop;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

/**
 * 🟢206 反转链表
 */
public class Solution206_02 {

    // 双指针法
    public ListNode reverseList(ListNode head) {
        // 构建虚拟头节点(拼接head链表)
        ListNode dummy = new ListNode(-1,head);
        ListNode pre = null;
        ListNode cur = dummy.next; // 构建遍历指针
        while(cur!=null){
            // ① 反转链表：将cur.next指向pre
            ListNode nxt = cur.next;
            cur.next = pre;
            // ② 更新pre、cur指针（更新下一个反转位置）
            pre = cur;
            cur = nxt;
        }
        // 返回反转后的链表
        return pre;
    }
}
