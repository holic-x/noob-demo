package com.noob.algorithm.solution_archive.leetcode.hot100.q234;

import com.noob.algorithm.solution_archive.leetcode.hot100.base.ListNode;

/**
 * 回文链表
 */
public class Solution2 {

    /**
     * 回文：因为链表长度不定，没办法类似数组这样去用双指针两头走进行判断
     * 因此此处就是正常的思路：正序遍历顺序=逆序遍历顺序 则满足回文
     * 正序：就是正常遍历链表
     * 逆序：类似链表反转的思路（可以用栈，或者头插法生成链表）
     */
    public boolean isPalindrome(ListNode head) {

        // 定义链表指针
        ListNode cur = head;

        // 遍历链表，利用头插法生成链表
        ListNode newNode = null; // 初始化节点为null（因为头插，则这个初始化的newNode最后会被放到尾巴处）
        while(cur!=null){
            newNode = new ListNode(cur.val,newNode); // 头插法构建新链表
            cur = cur.next; // 指针后移
        }

        // 再次正序遍历链表元素，然后依次和出栈元素进行比较
        while(head!=null){// 此处没有用新指针，而是直接用head作为指针了
            if(head.val != newNode.val){
                return false;
            }
            // 两个指针链表继续后移
            head = head.next;
            newNode = newNode.next;
        }
        return true;
    }

}
