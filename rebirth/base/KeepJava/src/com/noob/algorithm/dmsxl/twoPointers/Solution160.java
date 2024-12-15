package com.noob.algorithm.dmsxl.twoPointers;

import com.noob.algorithm.dmsxl.baseStructure.tree.ListNode;

/**
 * 160 链表相交
 */
public class Solution160 {

    /**
     * 双指针遍历：pointerA(A->B) pointer(B->A)
     * 如果两个链表存在公共链表（公共交点），则两个指针同时出发遍历最终会相遇（相遇的点要么是公共交点要么是null）
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 定义双指针遍历链表
        ListNode pointerA=headA;
        ListNode pointerB=headB;
        while(pointerA!=pointerB){
            // A指针遍历（A->B）
            if(pointerA!=null){
                pointerA = pointerA.next;// A指针继续往前
            }else{
                pointerA = headB; // 指向B链表继续遍历
            }

            // B指针遍历（B->A）
            if(pointerB!=null){
                pointerB = pointerB.next; // B指针继续往前
            }else{
                pointerB = headA; // 指向A链表继续遍历
            }
        }
        // 返回相遇点
        return pointerA;
    }
}
