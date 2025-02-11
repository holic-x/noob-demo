
package com.noob.algorithm.daily.plan02.day02;

import com.noob.algorithm.daily.base.ListNode;
import com.noob.algorithm.hot100.q015.Solution;

import java.util.Stack;

/**
 * 🟡 019 删除链表的倒数第N个节点 - https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/
 */
public class Solution019_03 {

    /**
     * 思路分析：双指针法（快慢指针）
     * 快指针走n步，随后快慢指针再一道出发，当快指针走到终点，就能找到len-n的位置（因为快慢指针始终相差n）
     * [1,2,3,4,5] => [-1,1,2,3,4,5] 结合案例理解指针定义（初始化、遍历）
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 构建虚拟头节点
        ListNode dummy = new ListNode(-1, head);
        // 定义遍历指针（快慢指针起点（要找到待删除节点的前置节点，所以slow的起点是dummy，而fast的起点是dummy.next））
        ListNode fast = dummy.next, slow = dummy;
        // ① 快指针先走n步
        while (n-- > 0) {
            fast = fast.next;
        }
        // ② 快慢指针同时出发，直到快指针到达终点
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // 此时slow指向待删除节点的前置节点
        slow.next = slow.next.next;
        // 返回处理后的结果
        return dummy.next;
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
        Solution019_03 solution = new Solution019_03();
        solution.removeNthFromEnd(node1, 2);
    }
}
