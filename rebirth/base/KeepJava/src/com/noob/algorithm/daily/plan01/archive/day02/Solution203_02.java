package com.noob.algorithm.daily.plan01.archive.day02;

import com.noob.algorithm.dmsxl.baseStructure.tree.ListNode;

/**
 * 🟢 203 移除链表元素
 * https://leetcode.cn/problems/remove-linked-list-elements/submissions/578999672/
 */
public class Solution203_02 {

    // 迭代法：迭代的过程中，找到满足Node.val == val 的前一个节点执行 cur.next = cur.next.next 即可删除指针
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        while (cur != null && cur.next != null) {
            // 判断是否满足删除条件
            if (cur.next.val == val) {
                cur.next = cur.next.next; // 删除val
            } else {
                cur = cur.next; // 指针后移（继续遍历下个节点）
            }
        }
        // 返回结果
        return dummy.next;
    }
}
