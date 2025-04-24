package com.noob.algorithm.daily.plan03.hot100_random.day01.p002;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

/**
 * 🟡 707 设计链表 - https://leetcode.cn/problems/design-linked-list/description/
 */
public class Solution707_01 {
}


/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
// 自定义单链表
class MyLinkedList {

    // 定义链表
    ListNode head;

    // 定义链表长度(有效的index集合，维护当前链表长度)
    int size;

    // 构造器：初始化链表
    public MyLinkedList() {
        head = new ListNode(-1);
    }

    // 获取指定索引位置的元素
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1; // 非法索引
        }

        // 遍历
        ListNode cur = head;
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }

        return cur.val;
    }

    // addAtHead: 头插
    public void addAtHead(int val) {
        // 构建新节点
        ListNode newNode = new ListNode(val);
        // 头插
        ListNode nxt = head.next;
        head.next = newNode;
        newNode.next = nxt;
        // size 更新
        size++;
    }

    // addAtTail: 尾插
    public void addAtTail(int val) {
        // 遍历到最后一个节点，随后执行尾插操作
        ListNode cur = head;
        for (int i = 0; i < size; i++) {
            cur = cur.next;
        }
        // 插入
        cur.next = new ListNode(val);
        // size 更新
        size++;
    }

    // addAtIndex: 指定位置插入
    public void addAtIndex(int index, int val) {
        // index 范围校验
        if (index < 0 || index >= size) {
            return;
        }

        // 遍历到指定位置，随后执行操作
        ListNode cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        // 插入
        ListNode newNode = new ListNode(val);
        ListNode nxt = cur.next;
        cur.next = newNode;
        newNode.next = nxt;
        // size 更新
        size++;
    }

    // deleteAtIndex:删除指定索引位置元素
    public void deleteAtIndex(int index) {
        // index 范围校验
        if (index < 0 || index >= size) {
            return;
        }

        // 寻找待删除节点的前驱节点
        ListNode cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        // size 更新
        size--;
    }

}