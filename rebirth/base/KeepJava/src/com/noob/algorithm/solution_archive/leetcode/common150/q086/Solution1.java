package com.noob.algorithm.solution_archive.leetcode.common150.q086;

import com.noob.algorithm.solution_archive.leetcode.common150.base.ListNode;

/**
 * 086 分隔链表
 */
public class Solution1 {

    /**
     * 思路：分拆链表存储，然后合并返回
     */
    public ListNode partition(ListNode head, int x) {
        // 定义两个链表分别存储[val<x][val>=x]的值
        ListNode smlDummy = new ListNode(-1);
        ListNode bigDummy = new ListNode(-1);
        // 定义链表指针
        ListNode curSml = smlDummy;
        ListNode curBig = bigDummy;

        // 遍历元素存储数据
        ListNode cur = head;
        while(cur!=null){
            // 根据链表节点值和x进行比较，追加到相应的链表
            if(cur.val<x){
                curSml.next = cur; // 追加节点到sml
                curSml = curSml.next; // sml指针后移
            }else{
                curBig.next = cur; // 追加节点到big
                curBig  = curBig.next; // big指针后移
            }
            // 遍历指针移动
            cur = cur.next;
        }

        // 遍历完成进行拼接
        curSml.next = bigDummy.next; // 将bigDummy链表拼接到curDummy链表后面
        curBig.next = null; // Error - Found cycle in the ListNode 异常处理
        // 返回结果
        return smlDummy.next;
    }

}

