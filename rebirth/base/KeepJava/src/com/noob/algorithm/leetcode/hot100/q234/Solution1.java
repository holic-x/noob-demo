package com.noob.algorithm.leetcode.hot100.q234;

import com.noob.algorithm.leetcode.hot100.base.ListNode;

import java.util.Stack;

/**
 * 回文链表
 */
public class Solution1 {


    /**
     * 回文：因为链表长度不定，没办法类似数组这样去用双指针两头走进行判断
     * 因此此处就是正常的思路：正序遍历顺序=逆序遍历顺序 则满足回文
     * 正序：就是正常遍历链表
     * 逆序：类似链表反转的思路（可以用栈，或者头插法生成链表）
     */
    public boolean isPalindrome(ListNode head) {

        // 定义链表指针
        ListNode cur = head;

        // 遍历链表，用栈存储元素
        Stack<ListNode> stack = new Stack<ListNode>();
        while(cur!=null){
            stack.push(cur); // 入栈
            cur = cur.next; // 指针后移
        }

        // 再次正序遍历链表元素，然后依次和出栈元素进行比较
        while(!stack.isEmpty()){ // 此处没有单独指定指针，将head作为指针了
            if(head.val != stack.pop().val){
                return false;
            }
            // 指针后移
            head = head.next;
        }

        return true;
    }

}
