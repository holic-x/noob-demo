package com.noob.algorithm.dmsxl.leetcode.q559;

import java.util.List;

/**
 * 559 N 叉树的最大深度
 */
public class Solution1 {

    public int maxDepth(Node root) {
        // 递归出口
        if (root == null) {
            return 0;
        }
        // 计算子节点的最大深度
        List<Node> children = root.children;
        int max = 0;
        for (Node node : children) {
            max = Math.max(max, maxDepth(node));
        }
        return max + 1;
    }

}

class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
