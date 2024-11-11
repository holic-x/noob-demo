package com.noob.algorithm.dmsxl.leetcode.q203;

import com.noob.algorithm.dmsxl.baseStructure.ListNode;

/**
 * 203 移除链表元素
 */
public class Solution2 {
    /**
     * 遍历删除（原地操作）
     */
    public ListNode removeElements(ListNode head, int val) {
        // 构建虚拟头节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head; // 给head补充一个虚拟头节点
        ListNode cur = dummy; // 定义遍历指针
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next; // 删除节点
            } else {
                // 指针后移
                cur = cur.next;
            }
        }
        // 返回链表
        return dummy.next;
    }
}
