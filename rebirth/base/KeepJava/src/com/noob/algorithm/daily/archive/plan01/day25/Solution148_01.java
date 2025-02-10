package com.noob.algorithm.daily.archive.plan01.day25;

import com.noob.algorithm.daily.base.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 🟡 148 排序链表 - https://leetcode.cn/problems/sort-list/description/
 */
public class Solution148_01 {

    /**
     * 思路分析：借助辅助集合进行排序
     */
    public ListNode sortList(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }

        // 对集合元素进行排序
        Collections.sort(list);

        // 基于排序后的元素序列构建新链表
        ListNode dummy = new ListNode(-1);
        ListNode pt = dummy;
        for (int i = 0; i < list.size(); i++) {
            pt.next = new ListNode(list.get(i));
            pt = pt.next;
        }
        pt.next = null; // handle cycle
        // 返回构建的链表
        return dummy.next;
    }
}
