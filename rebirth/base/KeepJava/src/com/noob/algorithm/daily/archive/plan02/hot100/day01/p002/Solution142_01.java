package com.noob.algorithm.daily.archive.plan02.hot100.day01.p002;

import com.noob.algorithm.daily.base.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 142 环形链表II - https://leetcode.cn/problems/linked-list-cycle-ii/description/
 */
public class Solution142_01 {
    /**
     * 思路分析：哈希法
     */
    public ListNode detectCycle(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode p = head;
        while (p != null) {
            // 如果节点已经存在于集合中，则存在环，返回环入口
            if (list.contains(p.next)) {
                return p.next;
            }
            list.add(p);
            p = p.next;
        }
        // 不存在环
        return null;
    }
}
