package com.noob.algorithm.daily.archive.plan02.day01.p002;

import com.noob.algorithm.daily.base.ListNode;

/**
 * 🟡 707 设计链表 - https://leetcode.cn/problems/design-linked-list/description/
 */
public class Solution707_01 {
}

/**
 * 构建单向链表：val（值）、next（下一个节点）
 * 构建双向链表：val（值）、next（下一个节点）、prev（上一个节点）
 */
class MyLinkedList1 {

    ListNode head; // 定义链表头节点
    ListNode tail; // 定义链表尾节点(todo 此处tail尾节点的意义不大，只需要head和链表长度即可)
    int count; // 维护当前链表元素个数

    public MyLinkedList1() {
        // 初始化
        head = new ListNode(-1, null); // 构建虚拟头节点
        tail = null;
        head.next = tail; // 初始化单链表
        count = 0;
    }

    // 获取指定索引的节点元素
    public int get(int index) {
        if (index > count) {
            return -1; // 指定索引超出链表节点个数
        }

        ListNode cur = head.next;
        // 从头节点开始遍历，获取指定索引index指向节点的值
        int cnt = 1;
        while (cur != null) {
            if (cnt == index) {
                return cur.val;
            } else {
                cnt++;
                cur = cur.next;
            }
        }
        // 不存在，返回-1
        return -1;
    }

    // 头插
    public void addAtHead(int val) {
        ListNode node = new ListNode(val);
        node.next = head.next;
        head.next = node;
        count++; // 插入新节点，链表节点统计个数+1
    }

    // 尾插
    public void addAtTail(int val) {
        // 遍历到第count个节点，进行尾插
        ListNode cur = head;
        for (int i = 0; i < count; i++) {
            cur = cur.next;
        }
        ListNode newNode = new ListNode(val);
        cur.next = newNode;
        newNode.next = tail;
    }

    // 插入指定索引位置
    public void addAtIndex(int index, int val) {
        // 判断当前插入位置与链表长度关系
        if (index == count) {
            // 插入微博
            addAtTail(val);
        } else if (index > count) {
            // 不执行任何插入操作
            System.out.println("无操作");
        } else if (index < count) {
            // 插入指定位置（找到下标为index的节点的前一个元素）
            ListNode cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            // 插入元素
            ListNode nxtNode = cur.next;
            ListNode newNode = new ListNode(val);
            cur.next = newNode;
            newNode.next = nxtNode;
            count++;
        }
    }

    public void deleteAtIndex(int index) {
        // 校验删除索引是否有效
        if (index < 0 || index > count) {
            return; // 删除索引无效，不执行任何操作
        } else {
            // 找到待删除节点的前一个节点，执行pre.next = pre.next.next
            ListNode cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
            count--;
        }
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