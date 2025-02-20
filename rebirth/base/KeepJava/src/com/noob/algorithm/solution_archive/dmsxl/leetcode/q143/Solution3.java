package com.noob.algorithm.solution_archive.dmsxl.leetcode.q143;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.ListNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 🟡143 重排链表
 */
public class Solution3 {

    // 求中点 + 后半部分反转 + 合并链表
    public void reorderList(ListNode head) {
        // ① 求中点(基于快慢指针)
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 当fast走到终点，slow位置此时对应为中点位置
        ListNode right = slow.next;
        slow.next = null; // 将终点位置断开，此时head就是前半部分的链表
        ListNode left = head; // 此处为了区分，用left接收

        // ② 后半部分反转(对right链表进行反转)
        ListNode pre = null;
        ListNode cur = right;
        while (cur != null) {
            ListNode nxt = cur.next; // 记录cur指向的下一个节点
            cur.next = pre; // 更改cur.next指针
            // 指针滚动更新，等待下一轮处理
            pre = cur;
            cur = nxt;
        }

        // ③ 合并链表(将left部分和反转后的right部分合并)
        ListNode dummy = new ListNode(-1);
        ListNode pd = new ListNode(-1);
        ListNode pointerL = left;
        ListNode pointerR = pre; // 此处pre对应为反转后的right链表
        int idx = 0;
        while (pointerL != null && pointerR != null) {
            if (idx % 2 == 0) {
                pd.next = pointerL;
                pd = pd.next;
                pointerL = pointerL.next;
                idx++;
            } else if (idx % 2 == 1) {
                pd.next = pointerR;
                pd = pd.next;
                pointerR = pointerR.next;
                idx++;
            }
        }

        if (pointerL != null) {
            pd.next = pointerL; // 直接拼接
        }

        if (pointerR != null) {
            pd.next = pointerR; // 直接拼接
        }
//        pd.next = null;

        // 处理结果
        head = dummy.next;
    }
}