package com.noob.algorithm.daily.plan03.hot100_random.day01.p002;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

/**
 * 🟢 203 移除链表元素 - https://leetcode.cn/problems/remove-linked-list-elements/description/
 */
public class Solution203_01 {

    /**
     * 思路分析：
     * 删除链表元素
     */
    public ListNode removeElements(ListNode head, int val) {
        // 构建虚拟头节点
        ListNode dummy = new ListNode(-1, head);

        // 遍历链表，移除元素
        ListNode cur = dummy;
        while (cur != null && cur.next != null) {
            if (cur.next.val == val) {
                // 如果找到待删除节点的前一个节点，移除链表元素
                cur.next = cur.next.next;
            }else{
                // 指针后移
                cur = cur.next;
            }
        }

        // 返回更新后的链表
        return dummy.next;
    }
}
