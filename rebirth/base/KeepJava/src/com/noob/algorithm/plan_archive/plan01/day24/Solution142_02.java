package com.noob.algorithm.plan_archive.plan01.day24;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

import java.util.HashMap;

/**
 * 🟡 142 环形链表II - https://leetcode.cn/problems/linked-list-cycle-ii/
 */
public class Solution142_02 {

    /**
     * 思路：哈希表
     */
    public ListNode detectCycle(ListNode head) {

        // 定义哈希表存储已遍历元素
        HashMap<ListNode, ListNode> map = new HashMap<>();

        ListNode cur = head;
        while (cur != null) {
            if (map.containsKey(cur.next)) {
                // return cur;
                // return map.get(cur.next); // 此处要返回环入口，因此返回的是map集合中的元素
                return cur.next; // 两个指向同一个元素也可用set存储，返回的是cur.next
            }
            map.put(cur, cur);
            cur = cur.next;
        }

        // 不存在环
        return null;
    }
}
