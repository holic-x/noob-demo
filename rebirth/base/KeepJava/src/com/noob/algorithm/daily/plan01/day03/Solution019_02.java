package com.noob.algorithm.daily.plan01.day03;

import com.noob.algorithm.dmsxl.baseStructure.tree.ListNode;

/**
 * 🟡019 删除链表的倒数第N个节点
 */
public class Solution019_02 {


    // 思路：计数法（计算链表长度）
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 构建辅助集合 List<ListNode> list = new ArrayList<>();
        int len = 0; // 此处计算链表长度即可
        // 构建虚拟头节点
        ListNode dummy = new ListNode(-1, head);
        // 遍历链表，将链表元素加入集合
        ListNode cur = dummy;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        // 遍历栈，找到第N-1个元素（删除倒数第N个节点，借助栈先入后出的特性处理，找到倒数第N-1个元素，即正向遍历栈的第N-1个元素）
        ListNode p = dummy; // 再遍历一次链表
        for (int i = 0; i < len - n - 1; i++) { // 因为多了一个-1的虚拟头节点
            p = p.next;
        }
        // 获取到栈的第n-1个元素
        ListNode prev = p;
        // 删除第N个元素
        prev.next = prev.next.next;
        // 返回处理后的链表
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

        Solution019_02 solution = new Solution019_02();
        solution.removeNthFromEnd(node1, 4);

    }

}
