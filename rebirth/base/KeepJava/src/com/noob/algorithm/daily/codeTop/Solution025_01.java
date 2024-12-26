package com.noob.algorithm.daily.codeTop;

import com.noob.algorithm.daily.base.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🔴 025 K个一组反转链表
 */
public class Solution025_01 {

    // K 个一组反转链表
    public ListNode reverseKGroup(ListNode head, int k) {
        // 正序遍历链表
        List<ListNode> list = new ArrayList<>();
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }

        // 遍历链表节点，K个为一组进行反转
        ListNode pre = list.get(0); // 记录每一组当前反转head节点的前一个节点
        ListNode nxt = null; // 记录每一组当前反转链表尾部节点的下一个节点
        for (int i = 1; i < list.size(); i = i + k) {
            if (i + k - 1 < list.size()) { // 不足K的剩余部分则跳过
                // 截取链表节点并进行反转，反转后重新拼接
                ListNode startNode = list.get(i);
                ListNode endNode = list.get(i + k - 1);

                // 此处需要先记录nxt，避免反转后修改
                nxt = endNode.next;
                endNode.next = null;

                // 反转链表并拼接
                pre.next = reverseLink(startNode);
                startNode.next = nxt;

                // 反转后的尾节点会作为下一组的反转链表的上一个节点
                pre = startNode;
            }
        }

        // 返回处理后的节点
        return dummy.next;
    }

    // 反转指定链表
    public ListNode reverseLink(ListNode node) {
        if (node == null) {
            return null;
        }

        ListNode pre = null;
        ListNode cur = node;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = pre;
            // 滚动更新pre、cur
            pre = cur;
            cur = nxt;
        }

        System.out.print("反转：");
        printLink(pre); // 打印反转后的链表
        System.out.println();

        // 返回反转后的链表
        return pre;
    }

    // 打印链表
    public void printLink(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + "->");
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        Solution025_01 s = new Solution025_01();
        ListNode head = s.reverseKGroup(node1, 2);
        s.printLink(head);

    }
}
