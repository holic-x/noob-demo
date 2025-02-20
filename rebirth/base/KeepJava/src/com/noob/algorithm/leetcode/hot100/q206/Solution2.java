package com.noob.algorithm.leetcode.hot100.q206;


/**
 * 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表
 */
public class Solution2 {

    public NewListNode reverseList(NewListNode head) {

        NewListNode res = null;

        // 自定义链表节点构造方法，类似头插概念，将链表节点插入在链表的头部。则此时只需要遍历链表元素
        NewListNode cur = head;
        while(cur!=null){
            res = new NewListNode(cur.val,res);
            cur = cur.next; // 指针后移
        }
        // 返回链表
        return res;
    }

}


/**
 * 自定义链表节点（类似头插法逻辑）
 */
class NewListNode {
    int val;
    NewListNode next;

    NewListNode(int val) {
        this.val = val;
    }

    // 构造方法（传入一个链表，将元素插在链表头部）
    NewListNode(int val, NewListNode next) {
        this.val = val;
        this.next = next;
    }

}
