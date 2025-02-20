package com.noob.algorithm.solution_archive.leetcode.common150.q230;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 230 二叉搜索树中第K小的元素
 */
public class Solution3 {

    // 定义全局变量用作递归计数统计、递归结果输出
    int res, count;

    public int kthSmallest(TreeNode root, int k) {
        this.count = k; // 初始化count计数器，当计数器减为0表示找到这个第k小的元素
        ldr(root); // 执行中序遍历，递归过程中进行计数
        return res; // 返回结果
    }

    /**
     * 中序遍历（left =》 root =》 right）
     */
    public void ldr(TreeNode node) {
        if (node == null) {
            return;
        }
        ldr(node.left); // left

        // 判断count计数是否达到k（count减为0）
        if (count == 0) {
            return;
        } else {
            // 记录值
            res = node.val;
            count--;
        }

        ldr(node.right); // right
    }
}
