package com.noob.algorithm.plan_archive.plan02.hot100.day01.p002;

import com.noob.algorithm.daily.base.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 019 删除链表的倒数第N个节点 - https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/
 */
public class Solution019_01 {

    /**
     * 思路分析：模拟法，获取到链表长度，然后找到 len - n - 1 （找到待删除节点的前一个节点）
     * 例如：[1,2,3,4,5] n=2，删除倒数第2个节点得到[1,2,3,5],那么应该找到第3（索引为5-2-1，len-n-1）个节点执行删除操作
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 构建虚拟头节点
        ListNode dummy = new ListNode(-1, head);
        // 定义遍历指针
        ListNode cur = dummy;
        List<ListNode> list = new ArrayList<>();
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }

        // 遍历节点
        int len = list.size();
        ListNode pre = list.get(len - n - 1);
        if (pre.next != null) {
            pre.next = pre.next.next;
        }

        // 返回处理后的链表
        return dummy.next;
    }
}
