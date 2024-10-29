package com.noob.algorithm.hot100.q019;

import com.noob.algorithm.hot100.base.ListNode;

import java.util.Stack;

/**
 * 019 删除链表的倒数第N个节点
 * ❌❌❌❌❌❌❌❌❌❌❌❌
 */
public class Solution1 {

    /**
     * 删除链表的倒数第N个节点
     * 链表长度未知无法通过倒序来索引删除，因此参考反转链表的思路，将链表放在栈中，取出栈顶第N个节点
     * 此处对应删除操作，只需要找到对应要删除的那个节点的前一个节点，然后将prv.next = prv.next.next 即可完成删除操作
     * 不需要又构建一遍链表重新遍历一遍
     * （此处是倒序遍历一遍删除第N个元素，对比之下多余了创建新链表的操作，必要性不大）
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // 定义虚拟头节点
        ListNode dummy = new ListNode(0);
        // 定义新链表的指针
        ListNode cur = dummy;

        Stack<ListNode> stack = new Stack<ListNode>();
        // 正序遍历链表，依次入栈
        while (head != null) {
            stack.push(head); // 入栈
            head = head.next; // 指针后移
        }

        // 依次弹出栈顶元素，计数到第N个不加入新链表
        int counter = 0;
        while (!stack.isEmpty()) {
            if (counter == n) {
                stack.pop();
            }else{
                cur.next = stack.pop();
                cur = cur.next;
            }
            counter++;
        }
        // 尾节点的next要置为null（处理：Error - Found cycle in the ListNode）
        cur.next = null;

        // 返回新构建的链表
        return dummy.next;
    }

}
