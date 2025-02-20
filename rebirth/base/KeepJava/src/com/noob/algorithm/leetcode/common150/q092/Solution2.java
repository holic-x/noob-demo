
package com.noob.algorithm.leetcode.common150.q092;

import com.noob.algorithm.leetcode.common150.base.ListNode;

/**
 * 反转链表 （一整个链表反转的思路）
 */
public class Solution2 {

    public ListNode reverse(ListNode head) {
        // 定义虚拟链表头
        ListNode dummy = null; // 此处其作为尾节点

        // 定义指针用于遍历head
        ListNode cur = head;
        while(cur!=null){
            dummy = new ListNode(cur.val,dummy); // 头插
            cur = cur.next; // 指针后移
        }

        // 返回结果
        return dummy;
    }

}
