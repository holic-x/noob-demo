package com.noob.algorithm.hot100.q019;

import com.noob.algorithm.hot100.q160.ListNode;

import java.util.Stack;

/**
 * 019 删除链表的倒数第N个节点
 */
public class Solution2 {

    /**
     * 正向思路：删除链表的倒数第N个节点，目的是为了找到这个第N-K的节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // 定义虚拟头节点
        ListNode dummy = new ListNode(0,head); // 初始化为head

        // 定义链表的指针
        ListNode cur = head;

        // 第一次遍历，获取链表长度
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        // 第二次遍历，找到要删除的节点位置进行删除操作
        ListNode delPointer = dummy;
        for (int i = 1; i < len - n + 1; i++) { // 从1开始，去掉虚拟头结点
            delPointer = delPointer.next;
        }
        // 此时delPointer对应的就是待删除节点的前一个节点的位置
        delPointer.next = delPointer.next.next;

        // 返回新构建的链表
        return dummy.next;
    }

}
