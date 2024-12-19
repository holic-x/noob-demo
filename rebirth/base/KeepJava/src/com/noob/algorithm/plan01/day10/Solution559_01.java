package com.noob.algorithm.plan01.day10;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢559 N叉树的最大深度
 */
public class Solution559_01 {

    // List<List<Integer>> res = new ArrayList<>();
    int maxLevel = 0; // 此处只需统计路径的最大值
    List<Integer> path = new ArrayList<>();

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }

        // 初始化将root节点值加入路径
        path.add(root.val);
        // 调用回溯算法
        backTrack(root);
        // 返回结果
        return maxLevel;
    }

    // 回溯法：获取所有路径，取最大深度（最长路径）
    public void backTrack(Node node) {
        // 递归出口
        if (node == null) {
            return;
        }

        // 当遍历到叶子节点，找到一条路径(叶子节点即表示children为空)
        if (node.children.isEmpty()) {
            // res.add(new ArrayList<>(path));
            maxLevel = Math.max(maxLevel, path.size());
        }

        // 递归回溯处理获取所有路径
        for (Node child : node.children) {
            if (child != null) {
                path.add(child.val); // 处理节点
                backTrack(child); // 递归
                path.remove(path.size() - 1); // 恢复现场
            }
        }
    }
}

// 定义N叉树节点 NTreeNode
class Node {
    int val;
    List<Node> children;
}
