package com.noob.algorithm.plan_archive.plan01.day25;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 🟡 199 二叉树的右视图
 */
public class Solution199_01 {

    /**
     * 思路分析：基于层序遍历的思路，其右视图对照的就是每一层的最末尾的元素，分层遍历将其载入结果集即可
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        // 定义队列辅助遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 遍历队列
        while (!queue.isEmpty()) {
            // 分层遍历
            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                TreeNode node = queue.poll();
                // 如果是当层最后一个元素方加入结果集
                if (i == curSize - 1) {
                    res.add(node.val);
                }
                // 处理其左右子节点
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        // 返回结果集
        return res;
    }

}
