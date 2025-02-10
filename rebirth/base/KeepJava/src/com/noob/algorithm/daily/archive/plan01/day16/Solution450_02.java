package com.noob.algorithm.daily.archive.plan01.day16;

import com.noob.algorithm.daily.base.TreeNode;
import com.noob.algorithm.dmsxl.graph.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟡 450 删除二叉搜索树中的节点
 */
public class Solution450_02 {


    /**
     * 核心思路：查找目标节点target（记录pre(父节点)） + 处理节点关系（构建删除后的新子树）
     * <p>
     * 删除二叉搜索树的节点，需注意对其子节点的处理（找到待删除节点，并处理子节点）
     * 1.左子节点left不为空，则left取代待删除节点的位置并将原来的右子节点放在最右
     * 2.右子节点right不为空，则right取代待删除节点的位置并将的原来的左子节点放在最左
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        return dfs(root, key);
    }

    public TreeNode dfs(TreeNode node, int key) {
        if (node == null) {
            return node;
        }
        // DLR 处理
        if (node.val == key) {
            TreeNode refreshNode;
            TreeNode leftNode = node.left;
            TreeNode rightNode = node.right;
            if (leftNode == null && rightNode == null) {
                refreshNode = null;
            } else if (leftNode != null && rightNode == null) {
                refreshNode = leftNode;
            } else if (leftNode == null && rightNode != null) {
                refreshNode = rightNode;
            } else {
                // 将左子节点挂载到右子节点的最左
                TreeNode findLeft = rightNode;
                while (findLeft.left != null) {
                    findLeft = findLeft.left;
                }
                findLeft.left = leftNode; // 将左子节点挂载到右子节点的最左
                refreshNode = rightNode;
            }
            return refreshNode; // 返回更新后的节点
        }

        // 递归处理左、右节点(根据node.val与key进行比较决定遍历方向)
        if (key < node.val) {
            node.left = dfs(node.left, key);
        } else if (key > node.val) {
            node.right = dfs(node.right, key);
        }

        return node;
    }

}
