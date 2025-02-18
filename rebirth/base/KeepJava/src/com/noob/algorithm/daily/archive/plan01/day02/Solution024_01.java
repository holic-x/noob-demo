package com.noob.algorithm.daily.archive.plan01.day02;

import com.noob.algorithm.dmsxl.baseStructure.tree.ListNode;

/**
 * 🟡 024 两两交换链表中的节点
 */
public class Solution024_01 {

    /**
     * 迭代法处理：两两交换链表中的节点（以1->2->3->4为例）
     * 第1次两两交换：2->1->3->4 即第一次交换是交换（1->2）随后下一次交换是从3开始（已经进行两两交换的元素不再重复参与）
     * 第2次两两交换：2->1->4->3 交换完毕
     * <p>
     * 构建虚拟头节点处理：dummy：x(cur)->1(first)->2(second)->3(third)->4 (交换first、second元素)
     * // 两两交换，更新指针
     * cur.next = second;
     * second.next = first;
     * first.next = third;
     * // cur指针后移，指向下一个要交换的元素位置（交换后的位置更新如下：x(cur)->2(second)->1(first)->3(third)->4）
     * cur = first (因为下一个要交换的元素是3，所以cur指向它的前一个位置，此处应为更新后的位置1（first）)
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return head;
        }

        // 构建虚拟头节点
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy; // 定义遍历指针，指向dummy

        // 遍历head链表，依次两两交换指针元素
        while (cur.next != null && cur.next.next != null) {
            ListNode first = cur.next;
            ListNode second = cur.next.next; // first.next
            ListNode third = cur.next.next.next; // second.next
            // 两两交换，更新指针
            cur.next = second;
            second.next = first;
            first.next = third;
            // cur指针后移，指向下一个要交换的元素
            cur = first;
        }

        // 返回交换后的链表
        return dummy.next;
    }

}
