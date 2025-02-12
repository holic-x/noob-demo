package com.noob.algorithm.daily.archive.plan02.day01.p002;

import com.noob.algorithm.daily.base.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 141 环形链表I - https://leetcode.cn/problems/linked-list-cycle/description/
 */
public class Solution141_01 {
    /**
     * 思路分析：哈希法（环形链表）
     */
    public boolean hasCycle(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode p = head;
        // 遍历链表
        while (p != null) {
            if (list.contains(p.next)) {
                return true; // p 指向的下一个节点已经出现过，因此链表存在环
            }
            list.add(p);
            p = p.next;
        }
        return false;
    }
}
