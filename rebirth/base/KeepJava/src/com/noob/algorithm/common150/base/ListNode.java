package com.noob.algorithm.common150.base;

/**
 * 链表节点定义
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) {
        val = x;
        next = null;
    }
    // 头插
    public ListNode(int x, ListNode next) {
        this.val = x;
        this.next = next;
    }
}