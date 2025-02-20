package com.noob.algorithm.plan_archive.baseStructure;

/**
 * 链表节点定义
 */
public class ListNode {
    public int val; // 节点值
    public ListNode next; // next指针
    // 无参构造函数
    public ListNode(){}
    // 带参构造函数
    public ListNode(int val){
        this.val = val;
        this.next = null;
    }
    // 带参构造函数(头插概念)
    public ListNode(int val,ListNode next){
        this.val = val;
        this.next = next;
    }

}
