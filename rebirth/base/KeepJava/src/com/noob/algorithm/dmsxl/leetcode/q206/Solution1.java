package com.noob.algorithm.dmsxl.leetcode.q206;

import com.noob.algorithm.dmsxl.baseStructure.tree.ListNode;

import java.util.Stack;

/**
 * 206 反转链表
 */
public class Solution1 {

    // 借助辅助集合（队列|栈）构建新链表
    public ListNode reverseList(ListNode head) {

        // 借助栈辅助
        Stack<ListNode> stack = new Stack<>();

        // 遍历链表
        ListNode cur = head;
        while(cur!=null){
            stack.push(cur);
            // 指针后移
            cur = cur.next;
        }

        // 遍历辅助栈（弹出元素，构建新链表）
        ListNode dummy = new ListNode(-1); // 定义虚拟头节点
        ListNode pointer = dummy; // 定义指针指向dummy
        while(!stack.isEmpty()){
            pointer.next = stack.pop(); // 弹出元素，构建链表
            pointer = pointer.next; // 指针移动
        }
        pointer.next = null; // 用于处理 Error - Found cycle in the ListNode

        // 返回构建的新链表
        return dummy.next;
    }
}
