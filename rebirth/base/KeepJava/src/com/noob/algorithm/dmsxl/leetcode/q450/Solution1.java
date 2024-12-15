package com.noob.algorithm.dmsxl.leetcode.q450;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

/**
 * 450 删除二叉搜索树中的节点
 */
public class Solution1 {

    // 迭代法：遍历树节点，找到要删除的目标节点，如果目标节点存在左右节点则需进行处理 todo 覆盖场景不足
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        // 遍历树节点，找到目标节点
        TreeNode cur = root;
        TreeNode pre = null; // 记录每个节点的上一个遍历节点
        while (cur != null) {
            if (key == cur.val) {
                if (pre == null) {
                    // 删除的是根节点:根据左右节点是否为空
                    if (cur.left == null && cur.right != null) {
                        return cur.right;
                    } else if (cur.right == null && cur.left != null) {
                        return cur.left;
                    } else if (cur.left != null && cur.right != null) {
                        TreeNode curLeft = cur.left;
                        TreeNode curRight = cur.right;
                        cur.left = null;
                        cur.right = null;

                        // 将curLeft挂到curRight最左节点
                        TreeNode findLeft = curRight;
                        while(findLeft!=null){
                            findLeft = findLeft.left;
                        }
                       // curRight.left = curLeft;
                        findLeft.left = curLeft;

                        return curRight; // 右子树作为根节点
                    } else {
                        return null;
                    }
                } else {
                    // 待删除节点为cur，先记录其left、right，然后将其左右节点挂载到pre上，保证二叉搜索树规则
                    TreeNode curLeft = cur.left;
                    TreeNode curRight = cur.right;

                    if (curLeft == null && curRight != null) {
                        return curRight;
                    } else if (curRight == null && curLeft != null) {
                        return curLeft;
                    } else if (curLeft != null && curRight != null) {
                        //                pre.left = curLeft;
//                curLeft.right = curRight;
                        pre.left = curRight;
                        curRight.left = curLeft;
                        return root;
                    } else {
                        pre.left = null;
                        pre.right = null;
                        return root;
                    }
                }
            } else if (key < cur.val) {
                pre = cur; // 更新pre
                cur = cur.left; // 去左边找
            } else if (key > cur.val) {
                pre = cur; // 更新pre
                cur = cur.right; // 去右边找
            }
        }
        // 返回处理后的结果
        return root;
    }
}
