package com.noob.algorithm.daily.plan03.hot100_random.day01.p002;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

import java.util.HashSet;

/**
 * 🟡 142 环形链表II - https://leetcode.cn/problems/linked-list-cycle-ii/description/
 */
public class Solution142_01 {
    /**
     * 思路分析：给定链表head，如果存在环则返回入环的第1个节点，如果链表无环则返回null
     */
    public ListNode detectCycle(ListNode head) {
        // 特例判断
        if (head == null) {
            return head;
        }

        // 定义哈希表存储已出现节点
        HashSet<ListNode> set = new HashSet<>();
        ListNode cur = head;
        while (cur != null) {
            if (set.contains(cur)) {
                return cur;
            }
            set.add(cur);
            cur = cur.next;
        }
        return null;
    }
}
