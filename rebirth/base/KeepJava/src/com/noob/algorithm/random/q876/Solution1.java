package com.noob.algorithm.random.q876;

import com.noob.algorithm.hot100.q160.ListNode;

/**
 * 876 如何找到链表中点
 */
public class Solution1 {

    // 起点相同：奇数选的是中点，偶数选的是第2个
    public ListNode finMid1(ListNode head) {
        // 定义快慢指针：两者同时从起点出发，然后慢指针走1步，快指针走2步，当快指针走到尾巴此时慢指针位置就是中点位置
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 起点不同（fast先走一步）：奇数选的是中点，偶数选的是第1个
    public ListNode finMid2(ListNode head) {
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(-1);
        head.next = new ListNode(5);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(0);
        Solution1 demo = new Solution1();
//        ListNode res1 = demo.finMid1(head);
//        System.out.println(res1.val);

        ListNode res2 = demo.finMid2(head);
        System.out.println(res2.val);
    }


}
