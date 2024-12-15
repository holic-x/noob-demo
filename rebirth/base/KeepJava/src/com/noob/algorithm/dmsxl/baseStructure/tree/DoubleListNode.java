package com.noob.algorithm.dmsxl.baseStructure.tree;

/**
 * 双向链表节点定义
 */
public class DoubleListNode {

    public int val ; // 链表节点值

    public DoubleListNode prev; // 链表prev节点指针

    public DoubleListNode next; // 链表next节点指针

    // 构造器
    public DoubleListNode(){}
    public DoubleListNode(int val){
        this.val = val;
        this.prev = null;
        this.next = null;
    }

    // 头插概念（如果传入的next是一个链表，则当前节点会插入到这个next指向链表前）
    public DoubleListNode(int val, DoubleListNode next){
        this.val = val;
        this.next = next;
    }

}
