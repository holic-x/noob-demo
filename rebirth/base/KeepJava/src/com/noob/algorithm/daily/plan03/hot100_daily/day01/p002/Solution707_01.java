package com.noob.algorithm.daily.plan03.hot100_daily.day01.p002;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

/**
 * 🟡 707 设计链表 - https://leetcode.cn/problems/design-linked-list/description/
 */
public class Solution707_01 {
}

// todo 

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */

class MyLinkedList {

    // 链表节点属性
    ListNode head;
    ListNode tail;
    int size; // 记录当前链表长度

    MyLinkedList() {
        head = null;
        tail = null;
    }

    // 获取指定索引节点信息
    public int get(int index) {
        ListNode p = head;
        while (index-- > 0) {
            p = p.next;
        }
        return p.val;
    }

    // 头插
    public void addAtHead(int val) {
        head = new ListNode(val, head);
        size++;
    }

    // 尾插
    public void addAtTail(int val) {
        tail = new ListNode(val);
        tail.next = null;
        size++;
    }


    // 插入指定位置
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            return; // 越界操作
        }
        // 插入元素到指定位置
        ListNode p = head;
        while (index-- > 0) {
            p = p.next;
        }
        ListNode nxt = p.next;
        ListNode newNode = new ListNode(val);
        p.next = newNode;
        newNode.next = nxt;
        size++;
    }

    // 删除指定位置
    public void deleteAtIndex(int index) {
        if (index < 0 || index > size) {
            return; // 越界操作
        }
        ListNode dummy = new ListNode(-1, head);
        ListNode p = dummy;
        while (index-- > 0) {
            p = p.next;
        }
        p.next = p.next.next;
        size--;
    }

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtTail(3);
        myLinkedList.addAtIndex(1, 2);    // 链表变为 1->2->3
        myLinkedList.get(1);              // 返回 2
        myLinkedList.deleteAtIndex(1);    // 现在，链表变为 1->3
        myLinkedList.get(1);              // 返回 3
    }

}