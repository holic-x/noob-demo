package com.noob.algorithm.dmsxl.q206;

import com.noob.algorithm.dmsxl.baseStructure.ListNode;

/**
 * 206 反转链表
 */
public class Solution4 {

    // 遍历+头插
    public ListNode reverseList(ListNode head) {
        ListNode dummy = null; // 其作为尾节点初始化为null（后面所有元素都会插入到其头部）
        ListNode cur = head;
        // 遍历节点依次头插
        while (cur != null) {
            dummy = new ListNode(cur.val, dummy);
            cur = cur.next;
        }
        return dummy; // pre 指向位置就是反转后的链表
    }

}
