package com.noob.algorithm.dmsxl.q206;

import com.noob.algorithm.dmsxl.baseStructure.ListNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 206 反转链表
 */
public class Solution3 {

    // 原地反转
    public ListNode reverseList(ListNode head) {
        ListNode pre = null; // 把它当做反转后链表的头节点，遍历head链表依次把链表元素给它加入到pre前
        ListNode cur = head;
        // 遍历节点依次进行反转
        while(cur!=null){
            ListNode nxt = cur.next; // 记录
            cur.next = pre; // 让 cur.next 指向 pre
            // 更改pre、cur（后移），等待下一次遍历
            pre = cur;
            cur = nxt;
        }
        return pre; // pre 指向位置就是反转后的链表
    }

}
