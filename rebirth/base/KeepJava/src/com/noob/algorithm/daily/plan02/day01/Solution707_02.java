
package com.noob.algorithm.daily.plan02.day01;

import com.noob.algorithm.daily.base.ListNode;
import com.noob.algorithm.dmsxl.util.PrintUtil;

/**
 * 🟡 707 设计链表 - https://leetcode.cn/problems/design-linked-list/description/
 */
public class Solution707_02 {

}

/**
 * 构建单向链表：val（值）、next（下一个节点）
 * 构建双向链表：val（值）、next（下一个节点）、prev（上一个节点）
 */
class MyLinkedList {

    ListNode head; // 定义链表头节点
    int curSize; // 维护当前链表元素个数（链表节点个数）

    public MyLinkedList() {
        // 初始化
        head = new ListNode(-1, null); // 构建虚拟头节点
        curSize = 0;
    }

    // 获取指定索引的节点元素
    public int get(int index) {
        // 校验索引的有效取值范围[0,curSize)
        if (index < 0 || index >= curSize) {
            return -1; // 指定索引不在有效范围内
        }

        ListNode cur = head;
        // 从头节点开始遍历，获取指定索引index指向节点的值
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }
        // 返回指定索引节点元素
        return cur.val;
    }

    // 头插
    public void addAtHead(int val) {
        // 构建新节点
        ListNode newNode = new ListNode(val);
        newNode.next = head.next;
        head.next = newNode;
        curSize++; // 插入新节点，链表节点统计个数+1
    }

    // 尾插
    public void addAtTail(int val) {
        // 遍历到最后一个节点，进行尾插
        ListNode cur = head;
        for (int i = 0; i < curSize; i++) {
            cur = cur.next;
        }
        ListNode newNode = new ListNode(val);
        cur.next = newNode;
        curSize++; // 插入新节点，链表节点统计个数+1
    }

    // 插入指定索引位置
    public void addAtIndex(int index, int val) {
        // 判断当前插入位置与链表长度关系
        if (index == curSize) {
            // 插入微博
            addAtTail(val);
        } else if (index > curSize) {
            // 不执行任何插入操作
            System.out.println("无操作");
        } else if (index < curSize) {
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
            curSize++;
        }
    }

    public void deleteAtIndex(int index) {
        // 校验删除索引是否有效
        if (index < 0 || index >= curSize) {
            return; // 删除索引无效，不执行任何操作
        } else {
            // 找到待删除节点的前一个节点，执行pre.next = pre.next.next
            ListNode cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
            curSize--;
        }
    }

    private void printLink() {
        ListNode cur = head;
        StringBuffer sb = new StringBuffer();
        while (cur != null) {
            sb.append(cur.val + "=>");
            cur = cur.next;
        }
        sb.append("end");
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.printLink();
        myLinkedList.addAtTail(3);
        myLinkedList.printLink();
        myLinkedList.addAtIndex(1, 2);
        myLinkedList.printLink();
        System.out.println(myLinkedList.get(1));
        myLinkedList.deleteAtIndex(1);
        myLinkedList.printLink();
        System.out.println(myLinkedList.get(1));
    }
}
