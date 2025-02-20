package com.noob.algorithm.plan_archive.plan01.day25;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

import java.util.Stack;

/**
 * 🟡019 - 删除链表的倒数第N个节点 - https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/
 */
public class Solution019_02 {

    /**
     * 思路：删除单链表节点㤇先找到其前置节点，随后设定pre.next = pre.next.next
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // 构建虚拟投建节点辅助删除操作（简化一些空节点处理）
        ListNode dummy = new ListNode(-1, head);

        // 借助辅助集合存储链表节点
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        // 弹出n个节点
        while(n-->0){
            stack.pop();
        }
        ListNode pre = stack.peek();
        // 删除节点
        if (pre.next != null) {
            pre.next = pre.next.next;
        }

        // 返回处理后的链表
        return dummy.next;
    }

}
