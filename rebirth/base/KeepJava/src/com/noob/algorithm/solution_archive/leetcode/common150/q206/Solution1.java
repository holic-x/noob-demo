package com.noob.algorithm.solution_archive.leetcode.common150.q206;

import com.noob.algorithm.solution_archive.leetcode.common150.base.ListNode;

/**
 * 反转链表：遍历反转
 */
public class Solution1 {
    public ListNode reverseList(ListNode head) {

        ListNode cur = head; // 定义遍历指针

        // 遍历并进行链表反转
        ListNode pre = null; // 初始化
        while(cur!=null){
            // 反转链表
            ListNode nxt = cur.next; // 记录链表的下一个节点
            cur.next = pre;
            // 指针往后移动等待下一轮反转
            pre = cur;
            cur = nxt;
        }

        // 返回结果
        return pre;
    }
}
