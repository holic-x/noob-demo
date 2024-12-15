package com.noob.algorithm.dmsxl.leetcode.q160;

import com.noob.algorithm.dmsxl.baseStructure.tree.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 160 相交链表
 */
public class Solution2 {
    // 哈希表（固定某个链表的所有元素到哈希表，随后遍历另一个链表判断是否存在交点）
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 定义哈希表
        Set<ListNode> setA = new HashSet<>();

        // 遍历链表A，将节点载入setA
        ListNode pointerA = headA;
        while(pointerA!=null){
            setA.add(pointerA);
            pointerA = pointerA.next;
        }

        // 遍历链表B，判断是否存在交点
        ListNode pointerB = headB;
        while(pointerB!=null){
            if(setA.contains(pointerB)){ // 存在交点
                return pointerB;
            }else{
                // 指针后移继续往下找
                pointerB = pointerB.next;
            }
        }

        // 无交点
        return null;
    }
}
