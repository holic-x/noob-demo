package com.noob.algorithm.solution_archive.leetcode.common150.q148;

import com.noob.algorithm.solution_archive.leetcode.common150.base.ListNode;

/**
 * 148 排序链表
 */
public class Solution1 {

    // 思路：归并排序（寻找链表中点，然后分别对中点左右两侧的链表进行排序，再将排序后的结果进行合并）
    public ListNode sortList(ListNode head) {
        return sortHeller(head);
    }


    public ListNode sortHeller(ListNode node) {
        // 递归出口
        if (node == null || node.next == null) {
            return node;
        }

        // 查找链表中点
        ListNode midNode = findMid(node);

        // 对右侧链表进行排序
        ListNode right = sortHeller(midNode.next);

        // 对左侧链表进行排序（断掉midNode的next即可，为了避免对右侧链表产生影响，此处需要先对右侧进行排序，或者先记录）
        midNode.next = null;
        ListNode left = sortHeller(node);

        // 返回排序后的合并结果(合并两个有序链表)
        return mergeLink(left, right);
    }


    // 查找链表中点：快慢指针思路
    public ListNode findMid(ListNode node) {
        ListNode slow = node;
        ListNode fast = node.next;
        while (fast != null && fast.next != null) {
            slow = slow.next; // 走1步
            fast = fast.next.next; // 走2步
        }
        return slow;
    }

    // 合并两个有序链表
    public ListNode mergeLink(ListNode link1, ListNode link2) {
        // 定义虚拟头节点
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy; // cur指针指向dummy

        // 定义指针分别用于遍历link1，link2
        ListNode point1 = link1;
        ListNode point2 = link2;

        while (point1 != null && point2 != null) {
            // 判断哪个链表指向的值比较小，则优先加入
            int val1 = point1.val;
            int val2 = point2.val;
            if (val1 <= val2) {
                // 加入新节点
                cur.next = new ListNode(val1);
                // 指针移动
                cur = cur.next;
                point1 = point1.next;
            } else {
                // 加入新节点
                cur.next = new ListNode(val2);
                // 指针移动
                cur = cur.next;
                point2 = point2.next;
            }
        }

        // 拼接剩余的链表
        if (point1 != null) {
            cur.next = point1;
        }
        if (point2 != null) {
            cur.next = point2;
        }

        // 返回合并后的结果
        return dummy.next;
    }
}
