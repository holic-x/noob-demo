package com.noob.algorithm.hot100.q148;

import com.noob.algorithm.hot100.base.ListNode;

/**
 * 148 链表排序
 */
public class Solution3 {

    /**
     * 自顶向下的归并排序：
     * 思路：将链表对半分，然后分别对左右两边进行排序，对排序完成的结果进行合并
     */
    public ListNode sortList(ListNode head) {

        // 递归出口
        if (head == null || head.next == null ) {
            return head;
        }

        // 寻找链表的中点（此处中点需为第1个，否则迭代过程中出错）
        ListNode finMid = finMid(head);

        // 右边链表
        ListNode right = sortList(finMid.next); // 右边链表的头节点就是中间节点的下一个节点

        // 左边链表
        finMid.next = null;
        ListNode left = sortList(head); // 左边链表的头节点就是head，但需要将中间节点断开才是前一半（所以此处需要先获取右边的，然后再拿左边）

        // 返回合并后的链表
        return mergeList(left, right);
    }

    // 查找链表中间节点
    public ListNode finMid(ListNode head) {
        // 此处中间节点获取有个点需要注意，即fast起点其实是先走1步（体现的效果就是偶数的时候选择的是两个中的第1个）
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 合并两个有序链表
    public static ListNode mergeList(ListNode l1, ListNode l2) {
        // 合并两个有序的链表：依次判断链表元素
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        // 定义两个链表的遍历指针
        ListNode pointer1 = l1;
        ListNode pointer2 = l2;

        // 遍历链表（当两个链表其中一个链表遍历到尾部，则可结束循环，然后将剩余的元素直接补到尾部即可）
        while (pointer1 != null && pointer2 != null) {
            // 如果pointer1指向元素小于等于pointer2指向元素，则加入链表
            if (pointer1.val <= pointer2.val) {
                cur.next = new ListNode(pointer1.val);
                // 指针后移
                pointer1 = pointer1.next;
                cur = cur.next;
            } else {
                // 加入pointer2指向元素
                cur.next = new ListNode(pointer2.val);
                // 指针后移
                pointer2 = pointer2.next;
                cur = cur.next;
            }
        }
        // 接入尾巴
        if (pointer1 != null) {
            cur.next = pointer1;
        }
        if (pointer2 != null) {
            cur.next = pointer2;
        }
        // 返回结果
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(-1);
        head.next = new ListNode(5);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(0);
        Solution3 solution3 = new Solution3();
        ListNode result = solution3.sortList(head);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

}
