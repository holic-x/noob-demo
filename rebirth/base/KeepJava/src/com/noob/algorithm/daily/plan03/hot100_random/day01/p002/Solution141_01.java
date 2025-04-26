package com.noob.algorithm.daily.plan03.hot100_random.day01.p002;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

import java.util.HashSet;

/**
 * 🟢 141 环形链表I - https://leetcode.cn/problems/linked-list-cycle/description/
 */
public class Solution141_01 {
    /**
     * 思路分析：给定一个链表的头节点head，判断链表中是否有环
     */
    public boolean hasCycle(ListNode head) {
        ListNode cur = head;

        // 定义哈希表存储已出现元素节点
        HashSet<ListNode> set = new HashSet<>();

        // 遍历链表
        while (cur != null) {
            if (set.contains(cur)) {
                return true;
            }
            set.add(cur);
            cur = cur.next;
        }

        // 不存在环
        return false;
    }
}
