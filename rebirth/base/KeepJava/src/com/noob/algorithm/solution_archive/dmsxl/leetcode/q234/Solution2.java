package com.noob.algorithm.solution_archive.dmsxl.leetcode.q234;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 🟢234 回文链表
 */
public class Solution2 {

    /**
     * 辅助栈 + 正序反序校验（校验一半的元素）
     */
    public boolean isPalindrome(ListNode head) {
        // 定义辅助栈存储链表元素
        Stack<Integer> stack = new Stack<>();
        // 遍历链表
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }

        // 再次遍历链表（正序）与栈中元素（逆序）一一校验，此处只需要校验一半的元素即可
        int cnt = stack.size() / 2;
        ListNode pointer = head; // 遍历指针
        for (int i = 0; i < cnt; i++) {
            if (pointer.val != stack.pop()) {
                return false;
            }
            pointer = pointer.next; // 指针后移，继续遍历下一位元素
        }

        // 校验通过
        return true;
    }
}
