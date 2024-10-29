package com.noob.algorithm.hot100.q019;

import com.noob.algorithm.hot100.base.ListNode;

import java.util.Stack;

/**
 * 019 删除链表的倒数第N个节点
 */
public class Solution3 {

    /**
     * 逆向思路：借助栈，逆向找第N个节点进行删除
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // 定义虚拟头结点
        ListNode dummy = new ListNode(0,head); // 初始化为head

        // 定义指针遍历链表,执行入栈操作
        ListNode cur = dummy;

        Stack<ListNode> stack = new Stack<ListNode>();
        while (cur.next != null) {
            stack.push(cur);
            cur = cur.next;
        }

        // 依次弹出栈的n-2个元素，获取到第n-1个元素修改其指针
        for(int i=0;i<n-1;i++){
            stack.pop();
        }
        // 获取到删除位置（删除节点的前一个节点pre，删除其指针）
        ListNode delPointer = stack.pop();
        delPointer.next = delPointer.next.next;

        return dummy.next;

    }
}
