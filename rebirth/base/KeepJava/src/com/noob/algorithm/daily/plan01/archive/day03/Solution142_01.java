package com.noob.algorithm.daily.plan01.archive.day03;

import com.noob.algorithm.dmsxl.baseStructure.tree.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 🟡 142 环形链表II
 */
public class Solution142_01 {

    /**
     * 思路：哈希表
     * 构建辅助集合存储已遍历元素，如果遍历节点重复出现则说明链表存在环
     */
    public ListNode detectCycle(ListNode head) {
        // 特例判断
        if (head == null) {
            return head;
        }

        // 构建辅助集合存储已遍历元素
        Set<ListNode> set = new HashSet<>();
        // 遍历链表
        ListNode cur = head;
        while (cur != null) {
            if (set.contains(cur)) {
                return cur; // cur为入环节点
            }
            set.add(cur);
            cur = cur.next;
        }
        return null;
    }
}
