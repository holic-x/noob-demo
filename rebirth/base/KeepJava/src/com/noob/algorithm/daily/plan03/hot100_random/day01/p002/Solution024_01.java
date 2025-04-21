package com.noob.algorithm.daily.plan03.hot100_random.day01.p002;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

/**
 * 🟡 024 两两交换链表中的节点 - https://leetcode.cn/problems/swap-nodes-in-pairs/description/
 */
public class Solution024_01 {

    /**
     * 思路分析：
     */
    public ListNode swapPairs(ListNode head) {
        // 构建虚拟头节点
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            ListNode first = cur.next;
            ListNode sec = cur.next.next;
            ListNode third = cur.next.next.next;
            // 交换节点
            cur.next = sec;
            sec.next = first;
            first.next = third;
            // cur指向下一个处理位置
            cur = first;
        }
        // 返回处理后的结果
        return dummy.next;
    }

}
