package com.noob.algorithm.daily.plan01.archive.day03;

import com.noob.algorithm.dmsxl.baseStructure.tree.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 🟡 142 环形链表II
 */
public class Solution142_02 {

    /**
     * 思路：哈希表
     * 构建辅助集合存储已遍历元素，如果遍历节点重复出现则说明链表存在环
     * todo :案例测试出现问题，此处还是切换到 【Solution142_01】的解法简单明了，不转圈圈
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        // Set存储已遍历元素
        Map<Integer, ListNode> visited = new HashMap<>();
        // 遍历链表，判断cur.next是否已经被访问过
        ListNode cur = head;
        while (cur != null) {
            if (visited.containsKey(cur.next.val)) {
                return visited.get(cur.next.val);
            }
            visited.put(cur.val, cur); // 将当前指针放入visited集合
            cur = cur.next; // 指针后移，继续下一个元素判断
        }
        // 所有节点遍历完成，说明不存在环
        return null;
    }
}
