package com.noob.algorithm.daily.plan01.archive.day26;

import com.noob.algorithm.daily.base.ListNode;

/**
 * 🟡 002 两数相加 - https://leetcode.cn/problems/add-two-numbers/description/
 */
public class Solution002_01 {

    /**
     * 思路分析：数字序列逆序存储在链表中，因此正序遍历链表然后依次处理和与进位关系即可
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // 构建虚拟头节点存储新构建的链表
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        // 定义指针遍历两个链表
        ListNode p1 = l1, p2 = l2;
        int carry = 0; // 进位处理（0表示无进位；1表示存在进位）
        while (p1 != null || p2 != null) { // 循环中处理空节点
            int val1 = (p1 != null ? p1.val : 0);
            int val2 = (p2 != null ? p2.val : 0);
            // 计算两个值，并处理进位关系
            int res = val1 + val2 + carry;
            // 构建新节点并更新进位
            cur.next = new ListNode(res % 10);
            carry = res / 10;
            // 更新指针(注意处理null值)
            cur = cur.next;
            if (p1 != null) {
                p1 = p1.next;
            }
            if (p2 != null) {
                p2 = p2.next;
            }
        }
        // 链表元素处理完成，补足最后的进位
        if (carry == 1) {
            cur.next = new ListNode(1);
            cur = cur.next;
        }
        cur.next = null; // handle cycle
        // 返回构建的新链表
        return dummy.next;
    }
}
