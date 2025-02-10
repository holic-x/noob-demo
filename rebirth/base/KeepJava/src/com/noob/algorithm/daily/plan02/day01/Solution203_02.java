
package com.noob.algorithm.daily.plan02.day01;

import com.noob.algorithm.daily.base.ListNode;

/**
 * 🟢 203 移除链表元素 - https://leetcode.cn/problems/remove-linked-list-elements/description/
 */
public class Solution203_02 {

    /**
     * 思路分析：迭代法（重新构建新链表，遇到target则跳过即可）
     * 删除链表元素（val）
     */
    public ListNode removeElements(ListNode head, int val) {
        // 构建虚拟头节点
        ListNode dummy = new ListNode(-1);
        ListNode pt = dummy;
        // 定义链表遍历指针
        ListNode cur = head;
        // 遍历链表元素
        while (cur != null) {
            // 判断当前链表节点值是否等于val，如果不相等则将其链接到新链表
            if (cur.val != val) {
                pt.next = new ListNode(cur.val);
                pt = pt.next;
            }
            // 指向下一个遍历节点
            cur = cur.next;
        }
        // 返回处理后的链表
        return dummy.next;
    }
}
