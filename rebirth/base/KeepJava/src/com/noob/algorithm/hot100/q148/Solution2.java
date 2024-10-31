package com.noob.algorithm.hot100.q148;

import com.noob.algorithm.hot100.base.ListNode;

/**
 * 148 链表排序
 */
public class Solution2 {

    /**
     * 自顶向下的归并排序：
     * 思路：将链表对半分，然后分别对左右两边进行排序，对排序完成的结果进行合并
     */
    public ListNode sortList(ListNode head) {

        // 递归出口
        if (head == null || head.next == null) {
            return head;
        }

        // 寻找链表的中点
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode middle = slow.next;
        slow.next = null;
        ListNode left = sortList(head); // 左边链表
        ListNode right = sortList(middle); // 右边链表
        // 返回合并后的链表
        return mergeList(left, right);
    }

    public static ListNode mergeList(ListNode l1, ListNode l2) {
        // 合并两个有序的链表：依次判断链表元素
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        // 定义两个链表的遍历指针
        ListNode pointer1 = l1;
        ListNode pointer2 = l2;

        // 遍历链表（当两个链表其中一个链表遍历到尾部，则可结束循环，然后将剩余的元素直接补到尾部即可）
        while (pointer1 != null && pointer2 != null) {
            // 如果pointer1指向元素小于等于pointer2指向元素，则加入链表
            if (pointer1.val <= pointer2.val) {
                cur.next = new ListNode(pointer1.val);
                // 指针后移
                pointer1 = pointer1.next;
                cur = cur.next;
            } else {
                // 加入pointer2指向元素
                cur.next = new ListNode(pointer2.val);
                // 指针后移
                pointer2 = pointer2.next;
                cur = cur.next;
            }
        }
        // 接入尾巴
        if (pointer1 != null) {
            cur.next = pointer1;
        }
        if (pointer2 != null) {
            cur.next = pointer2;
        }
        // 返回结果
        return dummy.next;
    }

}
