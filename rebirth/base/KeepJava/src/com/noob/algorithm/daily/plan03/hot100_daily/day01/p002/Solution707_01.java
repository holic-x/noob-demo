package com.noob.algorithm.daily.plan03.hot100_daily.day01.p002;

import com.noob.algorithm.plan_archive.baseStructure.ListNode;

/**
 * 🟡 707 设计链表 - https://leetcode.cn/problems/design-linked-list/description/
 */
public class Solution707_01 {

    public static void main(String[] args) {
        Solution707_01 solution = new Solution707_01();
        solution.testMyLinkedList();
    }

    public void testMyLinkedList() {
        /*
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtTail(3);
        myLinkedList.addAtIndex(1, 2);    // 链表变为 1->2->3
        myLinkedList.get(1);              // 返回 2
        myLinkedList.deleteAtIndex(1);    // 现在，链表变为 1->3
        myLinkedList.get(1);              // 返回 3
         */

        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtTail(3);
        myLinkedList.addAtIndex(1, 2);    // 链表变为 1->2->3
        System.out.println(myLinkedList.get(1)); // 返回 2
        myLinkedList.deleteAtIndex(1);    // 现在，链表变为 1->3
        System.out.println(myLinkedList.get(1)); // 返回 3
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
    class MyLinkedList {

        // 哨兵节点（头节点）
        ListNode head;
        // 当前链表有效节点个数
        int size;

        MyLinkedList() {
            head = new ListNode(-1); // 初始化一个虚拟头节点
            size = 0;
        }

        // 获取指定索引节点信息
        public int get(int index) {
            // 校验索引是否越界
            if (index < 0 || index >= size) {
                return -1;
            }

            ListNode p = head.next; // 从第1个真实节点开始计数
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            return p.val;
        }

        // 将新节点插入到链表的第一个节点位置
        public void addAtHead(int val) {
            // 构建新节点
            ListNode newNode = new ListNode(val, head.next);
            // 将链表加入到头部
            head.next = newNode;
            // 有效节点个数+1
            size++;
        }

        // 将新节点插入到链表尾部
        public void addAtTail(int val) {
            // 定义遍历指针
            ListNode p = head;
            // 遍历到链表尾部(或者p的null判断表示遍历到链表尾部)
            while (p.next != null) { // 从第1个真实节点开始遍历
                p = p.next;
            }
            // 尾部插入
            p.next = new ListNode(val);
            // 有效节点数+1
            size++;
        }


        // 插入指定位置（需要根据index与size的讨论情况分析）
        public void addAtIndex(int index, int val) {
            if (index < 0 || index > size) {
                return; // 越界操作
            }

            if (index == 0) {
                addAtHead(val);
                return; // 执行完成需退出操作
            }
            if (index == size) {
                addAtTail(val);
                return; // 执行完成需退出操作
            }

            // 插入元素到指定位置
            ListNode p = head;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            ListNode newNode = new ListNode(val, p.next);
            p.next = newNode;
            // 有效节点个数+1
            size++;
        }

        // 删除指定位置
        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) {
                return; // 越界操作
            }
            // 删除操作，寻找前置节点
            ListNode p = head;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            // 删除节点
            p.next = p.next.next;
            // 有效节点个数-1
            size--;
        }
    }

}