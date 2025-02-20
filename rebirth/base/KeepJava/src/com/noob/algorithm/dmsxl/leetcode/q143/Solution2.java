package com.noob.algorithm.dmsxl.leetcode.q143;

import com.noob.algorithm.dmsxl.baseStructure.tree.ListNode;
import com.noob.algorithm.leetcode.hot100.q015.Solution;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 🟡143 重排链表
 */
public class Solution2 {

    // 双向队列
    public void reorderList(ListNode head) {
        // 构建双向队列存储链表节点
        Deque<ListNode> deque = new LinkedList<>();
        // 遍历链表，入队
        ListNode cur = head;
        while (cur != null) {
            deque.offerLast(cur); // 插入队尾
            cur = cur.next;
        }

        // 根据队列大小填充新链表
        ListNode dummy = new ListNode(-1);
        ListNode pointer = dummy; // 定义指针指向dummy
        int curSize = deque.size(); // deque在遍历的过程中会取出元素，因此要先记录实际的链表节点个数
        for (int i = 0; i < curSize; i++) {
            if (i % 2 == 0) {
                // 偶数（取队头）
                pointer.next = deque.pollFirst();
                pointer = pointer.next;
            } else if (i % 2 == 1) {
                // 奇数（取队头）
                pointer.next = deque.pollLast();
                pointer = pointer.next;
            }
        }
        pointer.next = null; // handle [Error - Found cycle in the ListNode]

        head = dummy.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        Solution2 s = new Solution2();
        s.reorderList(node1);
    }
}