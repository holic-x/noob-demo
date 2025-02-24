package com.noob.algorithm.solution_archive.leetcode.common150.q148;

import com.noob.algorithm.solution_archive.leetcode.common150.base.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 148 排序链表
 */
public class Solution2 {

    // 思路：遍历+排序+覆盖（构建）
    public ListNode sortList(ListNode head) {

        // 构建集合存储链表元素
        List<Integer> list= new ArrayList<>();
        ListNode cur = head;
        while(cur!=null){
            list.add(cur.val);
            cur = cur.next;
        }

        // 对集合元素进行排序
        Collections.sort(list);

        // 构建链表
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        for(int val : list){
            p.next = new ListNode(val);
            p = p.next;
        }

        // 返回构建结果
        return dummy.next;
    }

}