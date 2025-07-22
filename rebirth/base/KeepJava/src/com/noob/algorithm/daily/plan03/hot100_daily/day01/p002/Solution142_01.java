package com.noob.algorithm.daily.plan03.hot100_daily.day01.p002;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 🟡 142 环形链表II - https://leetcode.cn/problems/linked-list-cycle-ii/description/
 */
public class Solution142_01 {
    /**
     * 思路分析：给定链表head，如果存在环则返回入环的第1个节点，如果链表无环则返回null
     * 处理：哈希表处理，将已遍历元素放入哈希表，如果目标遍历元素在现存的set中则说明存在环，返回set中存在的目标节点即可
     */
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode point = head;
        while (point != null) {
            if (set.contains(point)) {
                return point;
            }
            set.add(point);
            point = point.next;
        }
        return null;
    }
}
