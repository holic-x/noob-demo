package com.noob.algorithm.common150.q061;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        //边界情况
        if (head == null) {
            return null;
        }
        //统计链表长度len
        int len = 1;
        ListNode cur = head;
        while (cur.next != null) {
            len++;
            cur = cur.next;
        }
        //取模得到切割点
        int index = k % len;
        //index为0，则不需后面的反转操作
        if (index == 0) {
            return head;
        }
        //先整体反转
        ListNode newHead1 = reverse(head);
        //得到分开的两个链表头newHead1和newHead2
        ListNode cur1 = newHead1;
        for (int i = 0; i < index - 1; i++) {
            cur1 = cur1.next;
        }
        ListNode newHead2 = cur1.next;
        cur1.next = null;
        //两链表再次反转，并进行拼接
        ListNode newHead3 = reverse(newHead1);
        ListNode newHead4 = reverse(newHead2);
        ListNode cur2 = newHead3;
        for (int i = 0; i < index - 1; i++) {
            cur2 = cur2.next;
        }
        cur2.next = newHead4;
        //返回拼接后的链表
        return newHead3;
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }
}

作者：少年啊
链接：https://leetcode.cn/problems/rotate-list/solutions/1/duo-ci-fan-zhuan-lian-biao-0msjie-jue-by-9wmg/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。