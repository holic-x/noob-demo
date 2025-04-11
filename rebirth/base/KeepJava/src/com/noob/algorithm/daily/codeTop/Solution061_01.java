package com.noob.algorithm.daily.codeTop;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

/**
 * 🟡 061 旋转链表 - https://leetcode.cn/problems/rotate-list/description/
 */
public class Solution061_01 {

    /**
     * 给定链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置
     * 寻找分割点，分别反转这两部分然后进行拼接 ❌❌❌❌❌❌❌❌❌ 处理存在问题 ❌❌❌❌❌❌❌❌❌
     */
    public ListNode rotateRight(ListNode head, int k) {
        // 全反转
        ListNode allReverseLink = reverseLink(head);


        // 获取到前K个节点
        ListNode cur = allReverseLink; // cur 遍历指针初始化指向反转后的链表
        while (--k > 0) {
            cur = cur.next;
        }
        // 获取到截断的临界点
        ListNode curNode = cur.next;
        // 截断
        cur.next = null;
        ListNode reverseLeftLink = reverseLink(allReverseLink);
        ListNode reverseRightLink = reverseLink(curNode);
        // 重新拼接反转后的链表
        curNode.next = reverseRightLink;
        // 返回结果
        return reverseLeftLink;
    }

    // 反转单链表
    private ListNode reverseLink(ListNode node) {
        // 定义pre和cur指针
        ListNode pre = null, cur = node;
        // 遍历链表，反转处理
        while (cur != null) {
            ListNode nxt = cur.next; // 记录nxt指针
            cur.next = pre;
            // 指针移动
            pre = cur;
            cur = nxt;
        }
        // 返回反转后的链表结果
        return pre;
    }


}
