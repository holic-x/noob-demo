package com.noob.algorithm.daily.plan03.hot100_daily.day01.p002;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 🟢 141 环形链表I - https://leetcode.cn/problems/linked-list-cycle/description/
 */
public class Solution141_01 {
    /**
     * 思路分析：给定一个链表的头节点head，判断链表中是否有环
     * set 集合 哈希表思路：将已经出现过的元素加入集合，如果校验的目标元素在集合中存在则说明存在环
     */
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode point = head;
        while (point != null) {
            if (set.contains(point)) {
                return true;
            }
            set.add(point);
            point = point.next;
        }

        return false;
    }
}
