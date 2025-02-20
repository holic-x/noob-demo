package com.noob.algorithm.leetcode.common150.q230;

import com.noob.algorithm.leetcode.common150.base.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 230 二叉搜索树中第K小的元素
 */
public class Solution2 {

    public int kthSmallest(TreeNode node, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(node);
        while (node!=null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            // 当node为null（遍历到最左节点），此时弹出栈顶元素
            node = stack.pop();
            k--; // 计数-1（当K减为0，跳出循环）
            if (k == 0) {
                break;// 跳出循环，表示找到了这个第K小的元素
            }

            node = node.right;
        }
        // 当前指针指向的就是第K小的元素
        return node.val;
    }
}
