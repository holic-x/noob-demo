package com.noob.algorithm.solution_archive.leetcode.hot100.q148;

import com.noob.algorithm.solution_archive.leetcode.hot100.base.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 148 排序链表
 */
public class Solution1 {
    /**
     * 链表元素排序
     * 可以借助其他结合的工具类进行排序，然后构建新链表
     */
    public ListNode sortList(ListNode head) {

        // 定义虚拟链表头
        ListNode dummy = new ListNode(0);

        // 定义链表指针
        ListNode cur = dummy;

        // 遍历链表，然后加入集合，进行排序
        List<Integer> list = new ArrayList<>();
        while(head != null) {
            list.add(head.val);
            head = head.next;
        }
        // 排序
        Collections.sort(list);

        // 遍历排序后的集合，构建链表
        for (int val : list) {
            cur.next = new ListNode(val);
            cur = cur.next;
        }

        // 返回结果
        return dummy.next;
    }
}
