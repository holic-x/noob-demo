package com.noob.algorithm.dmsxl.leetcode.q102;

import com.noob.algorithm.dmsxl.baseStructure.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 102 二叉树的层序遍历
 */
public class Solution3 {

    public List<List<Integer>> levelOrder(TreeNode root) {
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
            res.add(curList);
        }

        // 返回结果集
        return res;
    }

}
