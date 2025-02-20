package com.noob.algorithm.solution_archive.dmsxl.leetcode.q669;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

/**
 * 669 修剪二叉搜索树
 */
public class Solution1 {

    /**
     * 递归法：
     * 判断每个遍历节点的值与边界的关系，然后结合二叉搜索树的特性进行处理：
     * 1.如果node.val<low 则说明node及其左子树都会小于low（超出边界，需排除），这种情况下返回的是【node的右子树经过递归修剪后的结果】
     * 2.如果node.val>low 则说明node及其右子树都会大于high（超出边界，需排除），这种情况下返回的是【node的左子树经过递归修剪后的结果】
     * 3.如果node.val取值在[low,high]区间范围内，则递归处理其左右节点，返回【node】
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        return dfsHelper(root, low, high);
    }

    // 构建辅助的dfs函数帮助处理树
    public TreeNode dfsHelper(TreeNode node, int low, int high) {
        // 递归出口
        if (node == null) {
            return null;
        }

        // 根据node.val与边界值的判断(超出边界则剪枝，未超出边界则构建左右节点)进行处理
        int curNodeVal = node.val;
        if (curNodeVal < low) {
            // 当前节点超出low边界左侧，则其左子树也会超出low边界左侧，因此此处返回的是【node的右子树经过递归修剪后的结果】
            return dfsHelper(node.right, low, high);
        } else if (curNodeVal > high) {
            // 当前节点超出high边界右侧，则其右子树也会超出high边界右侧，因此此处返回的是【node的左子树经过递归修剪后的结果】
            return dfsHelper(node.left, low, high);
        } else {
            // low<=curNodeVal<=high的情况，正常递归处理左右节点
            node.left = dfsHelper(node.left, low, high);
            node.right = dfsHelper(node.right, low, high);
            return node;
        }
    }
}
