package com.noob.algorithm.daily.plan02.day01.p002;

import com.noob.algorithm.daily.base.ListNode;

/**
 * 🟢 203 移除链表元素 - https://leetcode.cn/problems/remove-linked-list-elements/description/
 */
public class Solution203_01 {

    /**
     * 思路分析：迭代法
     * 删除链表元素（val）
     */
    public ListNode removeElements(ListNode head, int val) {
        // 构建虚拟头节点
        ListNode dummy = new ListNode(-1, head);
        // 定义链表遍历指针
        ListNode cur = dummy;
        // 遍历链表元素
        while (cur != null && cur.next != null) {
            // 校验cur的next节点的值是否与val相同
            if (cur.next.val == val) {
                // ① 如果cur.next为待删除节点,需要执行删除cur.next操作
                cur.next = cur.next.next;
            } else {
                // ② 指针移动指向下一个遍历元素继续校验
                cur = cur.next; // 指向下一个遍历元素
            }
        }
        // 返回处理后的链表
        return dummy.next;
    }
}
