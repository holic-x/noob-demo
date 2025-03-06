package com.noob.algorithm.daily.codeTop;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.ListNode;

/**
 * 🟡 链表求和 - https://leetcode.cn/problems/sum-lists-lcci/
 */
public class Solution0205_01 {

    /**
     * 数位反向存放在链表，用链表处理结果
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 定义虚拟头节点
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy; // 定义遍历指针

        // 定义双指针分别用于遍历l1、l2
        ListNode p1 = l1, p2 = l2;
        int carry = 0; // 定义进位
        while (p1 != null || p2 != null || carry == 1) {
            int val1 = p1 != null ? p1.val : 0;
            int val2 = p2 != null ? p2.val : 0;
            int sum = val1 + val2 + carry;
            // 判断sum是否溢出（超过十）
            if (sum >= 10) {
                cur.next = new ListNode(sum % 10);
                carry = sum / 10;
            } else {
                cur.next = new ListNode(sum);
                carry = 0; // 重置carry
            }
            cur = cur.next; // 指针指向下一位
            if (p1 != null) {
                p1 = p1.next;
            }
            if (p2 != null) {
                p2 = p2.next;
            }
        }

        // 返回构建的链表
        return dummy.next;
    }
}
