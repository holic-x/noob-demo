

package com.noob.algorithm.daily.plan02.day01.p002;

import com.noob.algorithm.daily.base.ListNode;

/**
 * 🟢 203 移除链表元素 - https://leetcode.cn/problems/remove-linked-list-elements/description/
 */
public class Solution203_03 {

    /**
     * 思路分析：递归法
     * 删除链表元素（val）
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }
}
