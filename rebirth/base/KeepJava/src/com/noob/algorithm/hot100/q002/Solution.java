package com.noob.algorithm.hot100.q002;

import com.noob.algorithm.hot100.q160.ListNode;

/**
 * 两数相加
 */
public class Solution {

    /**
     * 两个数字之和：数字存放时基于链表逆序存放的
     * 参考243+564=》708（进位补到后面的位置）
     * 也就是说正序遍历两个链表相应的元素，如果存在进位则添加进位，保留个位数作为元素存储
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // 定义虚拟链表头
        ListNode dummy = new ListNode(0);

        // 定义新链表指针
        ListNode cur = dummy;

        // 依次遍历两个链表元素，将对应位置数据元素进行相加(由于未知链表长度，所以对于比较短的链表此处可以通过补0来优化实现)
        ListNode pointer1 = l1, pointer2 = l2;
        boolean carry = false;

        // 由于链表长度未定，则此处设定是两个链表都遍历完成才退出循环，对于不足长度的链表则进行补0操作
        while (pointer1 != null || pointer2 != null) {
            int val1 = pointer1 == null ? 0 : pointer1.val; // 处理NPE
            int val2 = pointer2 == null ? 0 : pointer2.val; // 处理NPE
            int sum = val1 + val2 + (carry?1:0);
            // 获取个位数值
            int val = sum % 10; // 获取个位数值
            // 判断是否存在进位
            carry = (sum / 10 != 0) ; // 除数不为0说明带进位
            // 存储元素
            cur.next = new ListNode(val);

            // 操作完成指针均后移
            cur = cur.next;

            // 处理NPE
            if(pointer1 != null) pointer1 = pointer1.next;
            if(pointer2 != null) pointer2 = pointer2.next;
        }

        // 处理最后的进位
        if (carry) {
            cur.next = new ListNode(1);
        }

        // 返回链表
        return dummy.next;

    }

}
