package com.noob.algorithm.dmsxl.leetcode.q206;

import com.noob.algorithm.dmsxl.baseStructure.ListNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 206 反转链表
 */
public class Solution2 {

    // 借助辅助集合（队列|栈）构建新链表
    public ListNode reverseList(ListNode head) {

        // 借助集合辅助（队列）
        Deque<ListNode> queue = new LinkedList<>();

        // 遍历链表
        ListNode cur = head;
        while(cur!=null){
            // queue.push(cur);
            queue.offerFirst(cur); // offerLast
            // 指针后移
            cur = cur.next;
        }

        // 逆序遍历辅助队列（弹出元素，构建新链表）
        ListNode dummy = new ListNode(-1); // 定义虚拟头节点
        ListNode pointer = dummy; // 定义指针指向dummy
        while(!queue.isEmpty()){
            // pointer.next = queue.pop(); // 弹出元素，构建链表
            pointer.next = queue.pollFirst(); // 弹出元素，构建链表 pollLast
            pointer = pointer.next; // 指针后移
        }
        pointer.next = null; // 用于处理 Error - Found cycle in the ListNode

        // 返回构建的新链表
        return dummy.next;
    }
}
