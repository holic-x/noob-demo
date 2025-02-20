package com.noob.algorithm.plan_archive.plan01.day23;

import com.noob.algorithm.daily.base.ListNode;

import java.util.HashSet;

/**
 * 🟢 141 环形链表
 */
public class Solution141_01 {

    /**
     * 思路：哈希表思路
     */
    public boolean hasCycle(ListNode head) {
        // 定义集合存储节点
        HashSet<ListNode> set = new HashSet<>();
        // 遍历链表元素
        ListNode cur = head;
        while (cur != null) {
            if (set.contains(cur.next)) {
                return true; // 如果指针指向节点已经存在于集合中，则说明出现环
            }
            // 将节点加入集合，指针后移继续等待下一个位置校验
            set.add(cur);
            cur = cur.next;
        }
        // 遍历完成，不存在环
        return false;
    }
}
