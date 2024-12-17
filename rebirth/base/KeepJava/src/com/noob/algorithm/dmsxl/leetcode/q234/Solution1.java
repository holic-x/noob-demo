package com.noob.algorithm.dmsxl.leetcode.q234;

import com.noob.algorithm.dmsxl.baseStructure.tree.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢234 回文链表
 */
public class Solution1 {

    /**
     * 辅助集合 + 双指针校验回文
     */
    public boolean isPalindrome(ListNode head) {
        // 定义辅助集合存储链表值
        List<Integer> list = new ArrayList<>();

        // 遍历链表
        ListNode cur = head;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }

        // 校验回文（双指针）
        int start = 0, end = list.size() - 1;
        while (start <= end) {
            if (list.get(start) != list.get(end)) {
                return false; // 非回文
            }
            // 继续下一个校验
            start++;
            end--;
        }

        // 校验通过
        return true;
    }
}
