package com.noob.algorithm.common150.q061;

import com.noob.algorithm.common150.base.ListNode;

/**
 * 061 旋转链表
 */
public class Solution2 {

    /**
     * 思路：3次反转链表
     */
    public ListNode rotateRight(ListNode head, int k) {
        //边界情况
        if (head == null) {
            return null;
        }

        // 定义指针遍历链表，计算链表长度
        int len = 1;
        ListNode cur = head;
        while (cur.next != null) { // 从1开始计数
            len++;
            cur = cur.next;
        }

        // 计算移动步数(取模得到切割点)
        int idx = k % len;
        if (idx == 0) {
            return head; // 如果idx为0则说明不需要反转
        }

        // 第1次反转：整体反转
        ListNode reverseAll = reverse(head);

        // 获取切割的两个链表头（A、B）
        ListNode p = reverseAll; // 遍历指针
        for (int i = 0; i < idx - 1; i++) { // 找到切割位置
            p = p.next;
        }
        ListNode hA = reverseAll; // A 链表头
        ListNode hB = p.next; // B 链表头
        p.next = null; // 断开链表

        // 分别进行反转
        ListNode reverseA = reverse(hA);
        ListNode reverseB = reverse(hB);

        // 重新拼接链表
        ListNode pA = reverseA;
        for (int i = 0; i < idx - 1; i++) {
            pA = pA.next;
        }
        pA.next = reverseB; // A链表拼接B链表

        // 返回拼接后的链表
        return reverseA;
    }


    public ListNode reverse(ListNode head) {
        // 反转链表节点
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nxt = cur.next; // 记录下个元素
            cur.next = prev; // 反转
            // 更新指针信息
            prev = cur;
            cur = nxt;
        }
        // 返回反转后的链表
        return prev;
    }
}
