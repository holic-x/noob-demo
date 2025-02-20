package com.noob.algorithm.leetcode.common150.q023;

import com.noob.algorithm.leetcode.common150.base.ListNode;
import com.noob.algorithm.leetcode.common150.base.TreeNode;

import java.util.LinkedList;

/**
 * 023 合并K个有序链表
 */
public class Solution1 {

    // 思路：两两合并+辅助队列
    public ListNode mergeKLists(ListNode[] lists) {
        // 构建辅助队列处理链表
        LinkedList<ListNode> queue = new LinkedList();
        for (ListNode node : lists) {
            queue.offer(node);
        }

        // 边界条件判断，如果列表为空或者只有一个的情况
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }

        // 当辅助队列中存储的链表中元素超出2个则进行合并，直到只剩下一个
        while (queue.size() >= 2) {
            // 取出两个链表进行合并，并将合并后的结果追加到队列尾部
            ListNode link1 = queue.pop();
            ListNode link2 = queue.pop();
            ListNode mergeLink = merge(link1, link2);
            queue.offer(mergeLink);
        }

        // 最终返回队列中剩下的那个合并链表
        return queue.pop();
    }

    // 合并两个有序链表
    public ListNode merge(ListNode link1, ListNode link2) {
        // 构建虚拟头节点
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        // 定义两个待合并链表的遍历指针
        ListNode pointer1 = link1;
        ListNode pointer2 = link2;

        // 遍历链表
        while (pointer1 != null && pointer2 != null) {
            // 选择较小的元素加入
            int val1 = pointer1.val;
            int val2 = pointer2.val;
            if (val1 <= val2) {
                // 加入新节点
                cur.next = new ListNode(val1);
                // 指针后移
                cur = cur.next;
                pointer1 = pointer1.next;
            } else {
                // 加入新节点
                cur.next = new ListNode(val2);
                // 指针后移
                cur = cur.next;
                pointer2 = pointer2.next;
            }
        }
        // 拼接剩余的链表
        if (pointer1 != null) {
            cur.next = pointer1;
        }
        if (pointer2 != null) {
            cur.next = pointer2;
        }

        // 返回构建的链表
        return dummy.next;
    }
}
