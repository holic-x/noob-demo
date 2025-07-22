package com.noob.algorithm.daily.plan03.hot100_daily.day01.p002;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

/**
 * 🟢 203 移除链表元素 - https://leetcode.cn/problems/remove-linked-list-elements/description/
 */
public class Solution203_01 {

    /**
     * 思路分析：
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1, head);

        ListNode p = dummy;
        while (p != null && p.next != null) {
            // 处理节点
            if (p.next.val == val) {
                // 删除元素
                p.next = p.next.next;
            }else{
                // 指针移动
                p = p.next;
            }
        }
        // 返回更新后的数据
        return dummy.next;
    }
}
