package com.noob.algorithm.solution_archive.leetcode.common150.q023;

import com.noob.algorithm.solution_archive.leetcode.common150.base.ListNode;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 023 合并K个有序链表
 */
public class Solution2 {

    // 思路：最小堆（辅助集合+构建新链表）
    public ListNode mergeKLists(ListNode[] lists) {
        // 定义虚拟头节点
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy; // 新链表指针

        // 构建最小堆：将所有链表的头节点入堆
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a,b)->a.val-b.val); // 排序规则限定
        for(ListNode node : lists){
            if(node!=null){
                heap.add(node);
            }
        }

        // 遍历堆中元素
        while(!heap.isEmpty()){
            // 依次弹出堆中节点（如果其next存在则需要将其next节点入堆）
            ListNode node = heap.poll();
            if(node.next!=null){
                // 表示当前这个链表还不为空，需要将其next节点入堆
                heap.add(node.next);
            }

            // 构建链表
            cur.next = new ListNode(node.val);
            cur = cur.next; // 指针后移
        }

        // 返回结果
        return dummy.next;
    }


}
