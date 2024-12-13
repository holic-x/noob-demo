package com.noob.algorithm.dmsxl.leetcode.q707;

import com.noob.algorithm.dmsxl.baseStructure.tree.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 707 设计链表
 * 单链表方案
 */
class MyLinkedList {
    // 定义链表头节点和尾节点
    ListNode head;

    // 有效的index集合([0,curSize))
    int curSize; // 维护当前链表长度

    // 初始化链表
    public MyLinkedList() {
        head = new ListNode(-1); // 构建虚拟头节点
    }

    // 获取链表中下标为index的节点的值（下标无效返回-1）
    public int get(int index) {
        if (index < 0 || index >= curSize) {
            return -1;
        }
        // 遍历链表
        ListNode cur = head;
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    // 添加一个节点到头部
    public void addAtHead(int val) {
        // 构建新节点
        ListNode newNode = new ListNode(val);
        // 将链表加入到头部
        newNode.next = head.next;
        head.next = newNode;
        curSize++;
    }

    // 添加一个节点到尾部
    public void addAtTail(int val) {
        ListNode cur = head;
        for (int i = 0; i < curSize; i++) {
            cur = cur.next;
        }
        ListNode newNode = new ListNode(val);
        newNode.next = null;
        cur.next = newNode;
        curSize++;
    }

    // 插入一个节点到指定index位置之前（如果index==链表长度则插入尾部，如果index>链表长度则不执行插入）
    public void addAtIndex(int index, int val) {
        if (index == curSize) {
            addAtTail(val); // 添加到尾部
        } else if (index > curSize) {
            return;
        } else {
            ListNode cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            // 插入节点到指定位置
            ListNode newNode = new ListNode(val);
            ListNode nextNode = cur.next; // 记录cur.next
            cur.next = newNode;
            newNode.next = nextNode;
            curSize++;
        }
    }

    // 如果链表有效则删除链表中指定index的节点
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= curSize) {
            return;
        }
        // 删除链表指定index位置的节点
        ListNode cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        // 删除节点
        if (cur.next != null) {
            cur.next = cur.next.next;
            curSize--;
        }
    }

    public static void main(String[] args) {
        MyLinkedList obj = new MyLinkedList();
        obj.addAtHead(1);
        obj.printLink();
        obj.addAtHead(3);
        obj.printLink();
        obj.addAtTail(3);
        obj.printLink();
        obj.addAtIndex(1, 2);
        obj.printLink();
        System.out.println(obj.get(1));
        obj.deleteAtIndex(1);
        obj.printLink();
        System.out.println(obj.get(1));
    }

    public void printLink() {
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        System.out.println(list);
    }
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