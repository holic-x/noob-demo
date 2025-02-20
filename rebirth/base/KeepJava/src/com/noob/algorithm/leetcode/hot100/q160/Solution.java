package com.noob.algorithm.leetcode.hot100.q160;

import com.noob.algorithm.leetcode.hot100.base.ListNode;

/**
 * 160 相交链表
 */
public class Solution {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        /**
         * 定义两个指针：用于找公共链表节点
         */

        ListNode pointA = headA;
        ListNode pointB = headB;

        // 指针相遇则循环结束
        while (pointA != pointB) {
            // 遍历链表A，如果遍历到链表A尾部，则将其指向链表B
            if(pointA != null) {
                pointA = pointA.next;
            }else{
                pointA = headB;
            }

            // 遍历链表B，如果遍历到链表B尾部，则将其指向链表A
            if(pointB != null){
                pointB = pointB.next;
            }else{
                pointB = headA;
            }
        }
        // 此时指针指向的就是公共交点，返回pointA、pointB都可以
        return pointA;
    }

}



