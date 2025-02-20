package com.noob.algorithm.common150.q082;

import com.noob.algorithm.leetcode.hot100.base.ListNode;

/**
 * 082 删除链表中的重复元素
 */
public class Solution1 {

    /**
     * 思路：遍历链表元素（画图理解）
     * dummy->1->1->1->2->3
     * 遍历链表节点cur指向当前节点
     * 1.如果当前节点的后两个节点相等则说明出现重复需执行移除操作
     * 2.记录下这个重复的元素，如果cur.next.val为重复元素，则需移除cur后面这些重复的元素
     */
    public ListNode deleteDuplicates(ListNode head) {
        // 虚拟头节点定义，构建新链表用作遍历
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // 定义链表指针
        ListNode cur = dummy;

        // 遍历元素（判断cur后两个节点是否相同）
        while(cur.next!=null && cur.next.next!=null){
            // 判断cur后两个节点是否重复
            if(cur.next.val == cur.next.next.val){
                // 记录重复的元素，随后进行移除（即移除cur后面这些重复的元素）
                int x  = cur.next.val;
                while(cur.next!=null && cur.next.val == x){
                    cur.next = cur.next.next; // 删除cur的下一个节点
                }
            }else{
                // 不重复，指针继续后移
                cur = cur.next;
            }
        }

        // 返回链表
        return dummy.next;
    }
}
