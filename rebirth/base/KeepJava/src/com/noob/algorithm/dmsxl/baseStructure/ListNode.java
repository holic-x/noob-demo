package com.noob.algorithm.dmsxl.baseStructure;

/**
 * 链表节点定义
 */
public class ListNode {

    int val ; // 链表节点值

    ListNode next; // 链表next节点指针

    // 构造器
    ListNode(){}
    ListNode(int val){
        this.val = val;
        this.next = null;
    }

    // 头插概念（如果传入的next是一个链表，则当前节点会插入到这个next指向链表前）
    ListNode(int val,ListNode next){
        this.val = val;
        this.next = next;
    }
}
