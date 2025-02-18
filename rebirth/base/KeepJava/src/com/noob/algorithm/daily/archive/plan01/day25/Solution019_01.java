package com.noob.algorithm.daily.archive.plan01.day25;

import com.noob.algorithm.daily.base.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡019 - 删除链表的倒数第N个节点 - https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/
 */
public class Solution019_01 {


    /**
     * 思路：删除单链表节点㤇先找到其前置节点，随后设定pre.next = pre.next.next
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // 构建虚拟投建节点辅助删除操作（简化一些空节点处理）
        ListNode dummy = new ListNode(-1, head);

        // 借助辅助集合存储链表节点
        List<ListNode> list = new ArrayList<>();
        ListNode cur = dummy;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }

        // 正序找到第size-n-1个节点
        ListNode pre = list.get(list.size() - n - 1);
        // 删除节点
        if (pre.next != null) {
            pre.next = pre.next.next;
        }

        // 返回处理后的链表
        return dummy.next;
    }

}
