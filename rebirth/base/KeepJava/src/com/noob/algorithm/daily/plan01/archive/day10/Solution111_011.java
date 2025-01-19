package com.noob.algorithm.daily.plan01.archive.day10;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 111 二叉树的最小深度
 */
public class Solution111_011 {

    int minDepth = Integer.MAX_VALUE; // 记录[根节点->叶子节点]的每条路径的最小节点个数
    List<Integer> path = new ArrayList<>();

    // 规律分析：计算每个叶子节点的路径的节点个数，取最小值
    public int minDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 初始化将root节点加入路径
        path.add(node.val);

        // ① 调用dfs方法获取所有路径,得到最小的路径
        dfs(node);

        // ② 计算最小路径（在遍历路径的时候同步处理）
        return minDepth;
    }


    // 递归辅助计算根节点到每个叶子节点的路径的节点个数
    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        // 遇到叶子节点，添加路径
        if (node.left == null && node.right == null) {
            // 更新minDepth
            minDepth = Math.min(minDepth, path.size());
        }

        // 处理节点
        if (node.left != null) {
            path.add(node.left.val);
            dfs(node.left);
            path.remove(path.size() - 1);
        }
        if (node.right != null) {
            path.add(node.right.val);
            dfs(node.right);
            path.remove(path.size() - 1);
        }
    }
}
