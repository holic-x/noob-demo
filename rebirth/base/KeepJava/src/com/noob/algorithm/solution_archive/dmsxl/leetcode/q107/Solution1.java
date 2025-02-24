package com.noob.algorithm.solution_archive.dmsxl.leetcode.q107;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

import java.util.*;

/**
 * 107 二叉树的层序遍历II
 */
public class Solution1 {

    /**
     * - （1）层序遍历+头插：每遍历一层拿到遍历序列，将其头插到`res`的头部
     * - （2）层序遍历+反转：正常构建层序遍历，将最终的`res`反转即可得到"自底向上 从左到右"的遍历序列
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        // 结果集定义
        List<List<Integer>> res = new ArrayList<>();

        // 构建辅助队列
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root); // 初始化
        // 遍历队列
        while (!queue.isEmpty()) {
            // 遍历当层元素
            List<Integer> curList = new ArrayList<>();
            int curSize = queue.size();

            for (int i = 0; i < curSize; i++) {
                // 取出元素
                TreeNode cur = queue.poll();
                curList.add(cur.val);
                // 左右节点如果存在则分别入队
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }

            // 当层元素遍历完成，载入结果集
            // res.add(curList);
            res.add(0,curList); // 方案1：在指定位置插入结果集
        }

        // 返回结果集
        return res;
    }



}
