package com.noob.algorithm.daily.plan01.archive.day16;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟡 450 删除二叉搜索树中的节点
 */
public class Solution450_01 {

    /**
     * 核心思路：查找目标节点target（记录pre(父节点)） + 处理节点关系（构建删除后的新子树）
     * 删除二叉搜索树的节点，需注意对其子节点的处理（找到待删除节点，并处理子节点）
     * 1.左子节点left不为空，则left取代待删除节点的位置并将原来的右子节点放在最右
     * 2.右子节点right不为空，则right取代待删除节点的位置并将的原来的左子节点放在最左
     */
    public TreeNode deleteNode(TreeNode root, int key) {

        // ① 寻找目标target并记录其父节点
        TreeNode pre = null;
        TreeNode cur = root;
        while (cur != null) {
            // 校验cur.val与key的关系
            int curVal = cur.val;
            if (curVal == key) {
                break; // 跳出循环
            } else if (key < curVal) {
                pre = cur; // 记录pre
                cur = cur.left;
            } else if (key > curVal) {
                pre = cur; // 记录pre
                cur = cur.right;
            }
        }
        if (cur == null) {
            return root; // 目标节点不存在，直接返回
        }

        // ② 处理节点关系（校验目标节点的左右子树，构建新节点）
        TreeNode newChildNode;
        TreeNode curLeftNode = cur.left;
        TreeNode curRightNode = cur.right;
        if (curLeftNode == null && curRightNode == null) {
            // 左右子节点都为空，则删除该节点之后构建的新子节点为空
            newChildNode = null;
        } else if (curLeftNode == null && curRightNode != null) {
            // 左子节点为空，右子节点不为空，则右子节点上位
            newChildNode = curRightNode;
        } else if (curLeftNode != null && curRightNode == null) {
            // 左子节点不为空，右子节点为空，则左子节点上位
            newChildNode = curLeftNode;
        } else {
            // 左右子节点都不为空，可以将左子节点挂靠在右子节点的最左侧
            TreeNode findLeft = curRightNode;
            while (findLeft.left != null) {
                findLeft = findLeft.left;
            }
            findLeft.left = curLeftNode; // 将原左子节点挂靠在原右子节点的最左侧
            newChildNode = curRightNode; // 返回构建的新子节点
        }

        // ③ 拼接节点关系
        if (pre == null) {
            // 说明是初始化状态，那么只有待删除节点为root时才会出现，这种情况下直接返回构建的新节点
            return newChildNode;
        } else {
            // 校验pre.val与key的值，看目标值原来是在左侧还是右侧
            int preVal = pre.val;
            if (key < preVal) {
                pre.left = newChildNode;
            } else if (key > preVal) {
                pre.right = newChildNode;
            }
        }

        // 返回处理后的节点
        return root;
    }

}
