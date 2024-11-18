package com.noob.algorithm.dmsxl.leetcode.q098;

import com.noob.algorithm.dmsxl.baseStructure.TreeNode;

/**
 * 098 验证二叉搜索树
 */
public class Solution4 {

    int preVal = Integer.MIN_VALUE;// 定义当前遍历节点的上一个节点的值（此处只需要校验值，因此定义为数值类型，也可定义为TreeNode，但需注意NPE处理）

    public boolean isValidBST(TreeNode root) {
        return dfs(root);
    }

    // 中序遍历验证：中序遍历+相邻两数比较，此处引入pre优化空间复杂度，不需要定义列表存储所有的中序序列元素
    public boolean dfs(TreeNode node) {
        if (node == null) {
            return true;
        }
        // 左(校验左子树)
        if(!dfs(node.left)){
            return false;
        }

        // 中
        // 比较元素（判断cur和pre是否满足二叉搜索树条件）
        if(node.val<=preVal){
            return false; // 当前遍历节点值小于等于上一个节点值，非升序，返回false
        }
        // 更新pre节点值（当前节点值会作为下一个遍历节点的preVal）
        preVal = node.val;

        // 右(校验右子树)
        return dfs(node.right);
    }

}
