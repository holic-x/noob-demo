package com.noob.algorithm.daily.plan03.hot100_random.day01.p002;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

/**
 * 🟢 203 移除链表元素 - https://leetcode.cn/problems/remove-linked-list-elements/description/
 */
public class Solution203_02 {

    /**
     * 思路分析：
     * 删除链表元素
     */
    public ListNode removeElements(ListNode head, int val) {
        return delNode(head, val);
    }

    // 递归删除
    private ListNode delNode(ListNode node, int val) {
        // 递归出口
        if (node == null) {
            return node;
        }

        // 递归处理下一个节点
        node.next = delNode(node.next, val);

        return node.val == val ? node.next :node;
    }

}
