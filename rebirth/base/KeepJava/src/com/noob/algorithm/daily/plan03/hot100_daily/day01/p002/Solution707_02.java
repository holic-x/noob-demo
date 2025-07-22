package com.noob.algorithm.daily.plan03.hot100_daily.day01.p002;

/**
 * 🟡 707 设计链表 - https://leetcode.cn/problems/design-linked-list/description/
 */
public class Solution707_02 {

    public static void main(String[] args) {
        Solution707_02 solution = new Solution707_02();
        solution.testMyLinkedList();
    }

    public void testMyLinkedList() {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtTail(3);
        myLinkedList.addAtIndex(1, 2);    // 链表变为 1->2->3
        System.out.println(myLinkedList.get(1)); // 返回 2
        myLinkedList.deleteAtIndex(1);    // 现在，链表变为 1->3
        System.out.println(myLinkedList.get(1)); // 返回 3
    }

    /**
     * 双向链表节点定义
     */
    class ListNode {
        int val;
        ListNode prev;
        ListNode next;

        // 构造器
        ListNode(int val) {
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }

    class MyLinkedList {
        // 哨兵节点（头节点）
        ListNode head;
        // 哨兵节点（尾节点）
        ListNode tail;
        // 当前链表有效节点个数
        int size;

        // 初始化链表
        MyLinkedList() {
            size = 0;
            head = new ListNode(-1);
            tail = new ListNode(-1);
            // 构建连接关系
            head.prev = null;
            head.next = tail;
            tail.prev = head;
            tail.next = null;
        }

        // 根据索引获取指定节点
        public ListNode getNode(int index) {
            if (index < 0 || index >= size) return null;

            // 判断索引位置决定遍历顺序（类似二分检索概念）
            if (index < size / 2) {
                // 从前往后遍历
                ListNode curr = head.next;
                for (int i = 0; i < index; i++) {
                    curr = curr.next;
                }
                return curr;
            } else {
                // 从后往前遍历
                ListNode curr = tail.prev;
                for (int i = size - 1; i > index; i--) {
                    curr = curr.prev;
                }
                return curr;
            }
        }

        // 获取指定索引节点信息
        public int get(int index) {
            // 遍历节点获取指定索引位置元素(在getNode方法中统一校验索引的有效性)
            ListNode p = getNode(index);
            // 返回检索结果
            return p == null ? -1 : p.val;
        }

        // 将新节点插入到链表的第一个节点位置
        public void addAtHead(int val) {
            // 构建新节点
            ListNode newNode = new ListNode(val);
            // 构建节点连接关系（直接操作前后指针，无需额外定义指针）
            newNode.next = head.next;
            newNode.prev = head;
            head.next.prev = newNode;
            head.next = newNode;
            // 有效节点个数+1
            size++;
        }

        // 将新节点插入到链表尾部
        public void addAtTail(int val) {
            // 构建新节点
            ListNode newNode = new ListNode(val);

            // 直接操作前后指针（不用额外记录指针）
            newNode.prev = tail.prev;
            newNode.next = tail;
            tail.prev.next = newNode;
            tail.prev = newNode;

            // 有效节点数+1
            size++;
        }

        // 插入指定位置（需要根据index与size的讨论情况分析）
        public void addAtIndex(int index, int val) {
            // 校验index的有效性
            if (index < 0 || index > size) {
                return;
            }
            if (index == 0) {
                addAtHead(val);
            } else if (index == size) {
                addAtTail(val);
            } else {
                // 获取指定节点位置
                ListNode findNode = getNode(index);

                // 构建新节点并更新关系
                ListNode newNode = new ListNode(val);
                newNode.next = findNode;
                newNode.prev = findNode.prev;
                findNode.prev.next = newNode;
                findNode.prev = newNode;

                // 有效节点+1
                size++;
            }
        }

        // 删除指定位置
        public void deleteAtIndex(int index) {
            // 校验index的有效取值范围
            if (index < 0 || index >= size) {
                return;
            }

            // 获取目标节点
            ListNode findNode = getNode(index);
            findNode.prev.next = findNode.next;
            findNode.next.prev = findNode.prev;

            // 有效节点数-1
            size--;
        }
    }
}