package com.noob.algorithm.dmsxl.leetcode.q143;

import com.noob.algorithm.dmsxl.baseStructure.tree.ListNode;
import com.noob.algorithm.dmsxl.graph.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡143 重排链表
 */
public class Solution1 {

    // 辅助集合 +  双指针遍历
    public void reorderList(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        // 将链表节点装配到集合中进行处理
        ListNode cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }

        ListNode dummy = new ListNode(-1);
        ListNode pointer = dummy; // 定义指针指向dummy
        // 分别定义遍历指针用于从两个方向读取list的节点元素
        int leftIdx = 0, rightIdx = list.size() - 1;
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 0) {
                // 偶数位置依次填充正序遍历的节点
                pointer.next = list.get(leftIdx);
                pointer = pointer.next;
                leftIdx++;
            } else if (i % 2 == 1) {
                // 奇数位置依次填充逆序遍历的节点
                pointer.next = list.get(rightIdx);
                pointer = pointer.next;
                rightIdx--;
            }
        }
        pointer.next = null; // handle Error - Found cycle in the ListNode

        // 返回结果
        // return dummy.next;
        head = dummy.next;
    }
}