package com.noob.algorithm.dmsxl.leetcode.q203;

import com.noob.algorithm.dmsxl.baseStructure.ListNode;

/**
 * 203 移除链表元素
 */
public class Solution1 {
    /**
     * 构建新链表：遍历原链表，将【非val的节点】加入新链表
     */
    public ListNode removeElements(ListNode head, int val) {
        // 定义虚拟头节点
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy; // 构建指针指向dummy
        // 遍历链表head
        ListNode pointer = head;
        while(pointer!=null){
            if(pointer.val != val){
                // 如果值不为val则放入新链表
                cur.next = new ListNode(pointer.val);
                cur = cur.next; // 指针后移
            }
            pointer = pointer.next; // 指针后移
        }
        // 返回构建好的链表
        return dummy.next;
    }
}
