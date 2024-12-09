package com.noob.algorithm.plan01.day02;

import com.noob.algorithm.dmsxl.baseStructure.DoubleListNode;
import com.noob.algorithm.dmsxl.baseStructure.ListNode;

/**
 * 🟡 707 设计链表（自定义链表）
 * 不借助辅助的JAVA数据结构，仅借助自定义的ListNode构成自定义双向链表
 * 需注意此处双向链表节点需注意处理prev、next的值，双向链表需要始终维护一个head、tail
 */
public class Solution707_02 {

    public static void main(String[] args) {
        MyLinkedList obj = new MyLinkedList();
        obj.addAtHead(1);
        obj.printLink();
        obj.addAtTail(3);
        obj.printLink();
        obj.addAtIndex(1, 2);
        obj.printLink();
        System.out.println(obj.get(1));
        obj.deleteAtIndex(1);
        obj.printLink();
        System.out.println(obj.get(1));
        /*
        MyLinkedList obj = new MyLinkedList();
        obj.addAtHead(1);
        obj.printLink();
        obj.addAtHead(2);
        obj.printLink();
        obj.addAtTail(3);
        obj.printLink();
        obj.addAtTail(4);
        obj.printLink();
        obj.addAtIndex(5, 2);
        obj.printLink();
        System.out.println(obj.get(1));
        obj.deleteAtIndex(1);
        obj.printLink();
        System.out.println(obj.get(1));
         */
    }

}

/**
 * 自定义 双向链表
 */
class MyDoubleLinkedList {
    // 定义链表头节点
    DoubleListNode head;

    // 定义链表尾节点
    DoubleListNode tail;

    // 定义当前维护的链表长度（有效的index集合为[0,curSize））
    int curSize;

    // 构造方法
    MyDoubleLinkedList() {
        head = new DoubleListNode(-1); // 构造虚拟头节点，简化一些临界情况的讨论
        tail = new DoubleListNode();
        // 构建初始化链表
        head.next = tail;
        tail.prev = head;
    }

    // get：获取链表中下标为index节点的值
    int get(int index) {
        if (index < 0 || index >= curSize) {
            return -1; // 无效下标
        }
        // 遍历获取指定下标为index节点的值
        DoubleListNode cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.next.val; // 下标有效范围从1开始;
    }

    // addAtHead: 将一个值插入到第一个元素之前
    void addAtHead(int val) {
        DoubleListNode newNode = new DoubleListNode(val);
        newNode.next = head.next;
        head.next = newNode;
        curSize++; // 元素计数+1
    }

    // addAtTail: 将一个元素插入到链表末尾
    void addAtTail(int val) {
        // 遍历到链表末尾，进行插入
        DoubleListNode newNode = new DoubleListNode(val);
        // 获取尾节点的前一个元素
        DoubleListNode prev = tail.prev;
        prev.next = newNode;
        newNode.prev = prev;
        // 更新尾节点
        newNode.next = tail;
        // 元素计数+1
        curSize++;
    }

    // addAtIndex：将一个元素插入到下标为index的节点之前（如果index==len链表长度则该节点被追加到末尾，如果index>len则不会被插入）
    void addAtIndex(int index, int val) {
        // 根据指定index和curSize的大小判断处理方式
        if (curSize == index) {
            addAtTail(val); // 追加到末尾
        } else if (index < curSize) {
            DoubleListNode cur = head;
            while (index-- > 0) {
                cur = cur.next;
            }
            // 插入指定元素在index的位置
            DoubleListNode newNode = new DoubleListNode(val);
            DoubleListNode nxt = cur.next;
            cur.next = newNode;
            newNode.prev = cur;
            newNode.next = nxt;
            curSize++;
        } else if (index > curSize) {
            return;
        }
    }


    // deleteAtIndex：如果下标有效，则删除链表中下标为 index 的节点
    void deleteAtIndex(int index) {
        if (index < 0 || index >= curSize) {
            return; // 无效坐标，不做任何处理
        }

        // 遍历迭代删除
        DoubleListNode cur = head;
        while (index-- > 0) {
            cur = cur.next;
        }
        // 删除节点
        if (cur.next != null) {
            // 删除指定下标元素(更改指针)
            cur.next = cur.next.next;
            cur.next.prev = cur;
            curSize--; // 元素计数减1
        }
    }

    public void printLink() {
        DoubleListNode cur = head;
        while (cur != null) {
            System.out.print(" " + cur.val);
            cur = cur.next;
        }
        System.out.println("\t curSize：" + curSize);
    }

}
