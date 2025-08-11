package com.noob.algorithm.daily.plan03.hot100_daily.day05.p012;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 450 删除二叉搜索树中的节点 - https://leetcode.cn/problems/delete-node-in-a-bst/description/
 */
public class Solution450_01 {

    /**
     * 思路分析：❌ 当待删除节点为根节点，无法正确处理待删除节点为根节点的情况，需要进一步优化
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        dfs(root, key);
        return root;
    }

    /**
     * 基于DLR进行遍历，遍历过程中更新preNode的值（记录当前遍历节点的父节点）
     * 删除节点分析：
     * - 1.找到需要删除的节点
     * - 2.定位待删除节点的父节点和左右子节点，根据左右子节点的关系来处理节点
     * - - 如果L、R均不存在 直接删除
     * - - 如果L、R中只有一个存在，则选择存在的节点挂载到原父节点对应位置
     * - - 如果L、R均存在，则需将L挂载到R的最左，并将R作为新左节点挂载到原父节点对应位置
     *
     * @param args
     */

    private TreeNode preNode = null;

    private void dfs(TreeNode node, int key) {
        if (node == null) {
            return;
        }
        // node != null  校验当前节点是否为待删除节点
        if (node.val == key) {
            TreeNode newNode = getNewNode(node);
            if (preNode != null) {
                // 根据节点大小决定挂载位置
                if (newNode.val < preNode.val) {
                    preNode.left = newNode;
                } else if (newNode.val > preNode.val) {
                    preNode.right = newNode;
                }
            }
        }
        // 更新preNode
        preNode = node;
        // 递归处理节点
        dfs(node.left, key);
        dfs(node.right, key);
    }

    // 获取删除node节点后新上位的节点，该节点会被作为新的节点挂载到原父节点的位置上
    private TreeNode getNewNode(TreeNode node) {
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;
        if (leftNode == null && rightNode == null) {
            System.out.println("no handle");
            return null;
        } else if (leftNode != null && rightNode != null) {
            TreeNode leftIdx = rightNode;
            while (leftIdx.left != null) {
                leftIdx = leftIdx.left;
            }
            leftIdx.left = leftNode;
            return rightNode;
        } else {
            return leftNode == null ? rightNode : leftNode;
        }
    }


}
