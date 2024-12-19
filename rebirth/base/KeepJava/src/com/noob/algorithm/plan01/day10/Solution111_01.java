package com.noob.algorithm.plan01.day10;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 111 二叉树的最小深度
 */
public class Solution111_01 {

    public List<List<Integer>> res = new ArrayList<>();
    // 此处不同于【129 求根节点到叶节点数字之和】，不可以采用字符串拼接（存在负数的情况，且数值大小不限制，直接用拼接的方式计算path长度的话就会出现问题）
    // public StringBuffer path = new StringBuffer();
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

        // ② 计算最小路径
        int minDepth = Integer.MAX_VALUE;
        for (List<Integer> path : res) {
            minDepth = Math.min(path.size(), minDepth);
        }
        return minDepth;
    }


    // 递归辅助计算根节点到每个叶子节点的路径的节点个数
    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        // 遇到叶子节点，添加路径
        if (node.left == null && node.right == null) {
            res.add(new ArrayList<>(path)); // 此处注意对象引用问题，添加一个新的String对象，避免回溯过程中的对象引用变化
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

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(-8);
        TreeNode node2 = new TreeNode(-6);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node4.right = node5;
        Solution111_01 s = new Solution111_01();
        s.minDepth(node1);
    }
}
