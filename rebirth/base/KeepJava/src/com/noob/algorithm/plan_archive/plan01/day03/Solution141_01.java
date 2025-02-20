package com.noob.algorithm.plan_archive.plan01.day03;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 🟡 141 环形链表I
 * https://leetcode.cn/problems/linked-list-cycle/description/
 */
public class Solution141_01 {

    /**
     * 思路：哈希表（重复判断）
     * 基于遍历链表的思路，校验遍历指针cur指向元素，如果元素已经在集合中出现过则说明存在环
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        // Set存储已遍历元素
        Set<ListNode> visited = new HashSet<>();
        // 遍历链表，判断cur.next是否已经被访问过
        ListNode cur = head;
        while (cur != null) {
            if (visited.contains(cur)) {
                return true;
            }
            visited.add(cur); // 将当前指针放入visited集合
            cur = cur.next; // 指针后移，继续下一个元素判断
        }
        // 所有节点遍历完成，说明不存在环
        return false;
    }
}
