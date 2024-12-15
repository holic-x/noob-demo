package com.noob.algorithm.dmsxl.leetcode.q098;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

/**
 * 098 验证二叉搜索树
 */
public class Solution2 {

    // todo 存在问题
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return dfs(root, Integer.MIN_VALUE);
    }

    // 中序遍历验证升序(在递归的过程中进行比较)
    public boolean dfs(TreeNode node, int lastNodeVal) {
        if (node == null) {
            return true;
        }

        // 如果当前遍历节点<=lastNodeVal,说明非升序，则直接返回false
        if (node.val <= lastNodeVal) {
            return false;
        }

        // 递归校验左右子树(此时node.val作为下个遍历节点的lastNodeVal)
        if(!dfs(node.left, node.val)){
            return false;
        }

        return dfs(node.right, node.val);
    }

}
