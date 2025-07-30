package com.noob.algorithm.daily.plan03.hot100_daily.day05.p012;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 450 删除二叉搜索树中的节点 - https://leetcode.cn/problems/delete-node-in-a-bst/description/
 */
public class Solution450_02 {

    /**
     * 迭代法
     * 思路分析：进一步分拆逻辑，基于迭代思路进行处理
     * - ① 找到需要删除的节点：结合二叉搜索树特性二分搜索寻找目标节点（待删除节点），并记录其父节点（LDR）
     * - - - 此处需注意用局部变量来处理过程节点，避免全局影响导致问题
     * - ② 定位待删除节点的父节点和左右子节点，根据左右子节点的关系来处理节点：根据目标节点的子节点情况分类讨论，获取到最新上位的新子树根节点newSubRoot
     * - - - 如果L、R均不存在 直接删除
     * - - - 如果L、R中只有一个存在，则选择存在的节点挂载到原父节点对应位置
     * - - - 如果L、R均存在，则需将L挂载到R的最左，并将R作为新左节点挂载到原父节点对应位置
     * - ③ 根据值大小比较选择将newSubRoot挂载到父节点preNode的指定位置即可
     * - - - 此处的原位置可以通过preNode.left==findNode来判断，得到待删除目标节点的原位置（直接根据原父子关系进行挂载）
     * - - - 是否可以用值来定位：BTS的特性本身就保证了节点的左右子树大小关系，如果cur在左侧则其子节点必然也会小于preNode的，同理右侧也是，所以也可以根据值定位挂载位置
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        // ① 寻找目标节点
        TreeNode cur = root;
        TreeNode preNode = null;
        while (cur != null && cur.val != key) {
            preNode = cur;
            if (key < cur.val) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }

        // 校验目标节点是否存在
        if (cur == null) {
            return root; // 目标节点不存在不需要执行任何操作，返回原树
        }

        // ② 获取如果删除目标节点上位的子节点
        TreeNode newSubRoot = getNewNode(cur);

        // ③ 根据位置挂载目标
        if (preNode == null) {
            return newSubRoot; // 待删除节点是根节点，则获取到的新子树节点即为所得
        } else {
            // 根据原位置决定挂载方向（左或右）
            if (preNode.left == cur) {
                preNode.left = newSubRoot;
            } else {
                preNode.right = newSubRoot;
            }
        }

        // 返回更新结果
        return root;
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
