package com.noob.algorithm.plan_archive.plan01.day03;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.ListNode;

import java.util.Stack;

/**
 * 🟡019 删除链表的倒数第N个节点
 */
public class Solution019_01 {


    // 思路：辅助栈
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 构建辅助栈
        Stack<ListNode> stack = new Stack<>();
        // 构建虚拟头节点
        ListNode dummy = new ListNode(-1, head);
        // 遍历链表，将链表元素加入stack
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        // 遍历栈，找到第N-1个元素（删除倒数第N个节点，借助栈先入后出的特性处理，找到倒数第N-1个元素，即正向遍历栈的第N-1个元素）
        for (int i = 0; i < n; i++) { // 因为多了一个-1的虚拟头节点，所以此处遍历到n的位置
            stack.pop();
        }
        // 获取到栈的第n-1个元素
        ListNode prev = stack.pop();
        // 删除第N个元素
        prev.next = prev.next.next;
        // 返回处理后的链表
        return dummy.next;
    }

    public ListNode removeNthFromEnd1(ListNode head, int n) {

        // 定义虚拟头结点
        ListNode dummy = new ListNode(0, head); // 初始化为head

        // 定义指针遍历链表head,执行入栈操作
        ListNode cur = dummy;
        Stack<ListNode> stack = new Stack<ListNode>();
        while (cur.next != null) {
            stack.push(cur);
            cur = cur.next;
        }

        // 依次弹出栈的n-2个元素，获取到第n-1个元素修改其指针
        for (int i = 0; i < n - 1; i++) {
            stack.pop();
        }
        // 获取到删除位置（删除节点的前一个节点pre，删除其指针）
        ListNode delPointer = stack.pop();
        delPointer.next = delPointer.next.next;

        return dummy.next;

    }

    public static void main(String[] args) {
        // 构建节点
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        // 构建节点关系
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        Solution019_01 solution = new Solution019_01();
        solution.removeNthFromEnd(node1, 4);

    }

}
