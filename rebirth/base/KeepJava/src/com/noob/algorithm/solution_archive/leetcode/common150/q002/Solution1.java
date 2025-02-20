package com.noob.algorithm.solution_archive.leetcode.common150.q002;

import com.noob.algorithm.solution_archive.leetcode.common150.base.ListNode;

/**
 * 002 两数相加
 */
public class Solution1 {
    /**
     * 243+564=708
     * 将链表元素按位对照相加，如果存在进位则放在下一个位置的相加中，直到某个链表遍历结束，最终将剩余的链表进行拼接
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // 定义虚拟链表头
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy; // 定义新链表指针

        // 分别定义两个待相加的链表指针
        ListNode p1 = l1;
        ListNode p2 = l2;

        // 遍历链表，当两个链表都不为空时，按位相加
        int carry = 0; // 是否存在进位
        while (p1 != null || p2 != null) { // 为了不需额外处理多出来的链表，此处对于空节点的值可以置为0
            int val1 = p1 == null ? 0 : p1.val;
            int val2 = p2 == null ? 0 : p2.val;
            int sum = val1 + val2 + carry;
            // 定义新节点存储相加后的值
            ListNode node = new ListNode(sum >= 10 ? (sum % 10) : sum);
            cur.next = node;
            cur = cur.next; // 指针指向下一位
            // 更新进位信息
            carry = sum / 10;

            // 指针后移
            if(p1!=null){
                p1 = p1.next;
            }
            if(p2!=null){
                p2 = p2.next;
            }
        }

        // 需要将最终的进位补上(也可以将其放在上面的while条件中就不用单独拎出来)
        if(carry==1){
            cur.next = new ListNode(1);
        }

        // 返回构建的新链表
        return dummy.next;
    }
}
