package com.noob.algorithm.daily.codeTop;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 🔴 297 二叉树的序列化与反序列化 - https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/description/
 */
public class Solution297_02 {

}


class Codec {

    // (1)将一个二叉树【序列化】为String字符串形式（BFS）
    public String serialize(TreeNode root) {
        StringBuffer ans = new StringBuffer();
        // 构建队列辅助遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            // 拼接节点
            if (node == null) {
                ans.append("#").append(",");
            } else {
                ans.append(node.val).append(",");
                // 将子节点加入
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return ans.toString();
    }

    private TreeNode getNode(String val) {
        if (val.equals("#")) {
            return null;
        }
        return new TreeNode(Integer.valueOf(val));
    }

    // (2)将一个String形式的内容【反序列化】为二叉树(BFS)
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        // 构建节点
        TreeNode root = getNode(nodes[0]);
        Queue<TreeNode> parents = new LinkedList();
        TreeNode parent = root;
        boolean isLeft = true;
        for (int i = 1; i < nodes.length; i++) {
            TreeNode cur = getNode(nodes[i]);
            if (isLeft) {
                parent.left = cur;
            } else {
                parent.right = cur;
            }
            if (cur != null) {
                parents.add(cur);
            }
            isLeft = !isLeft; // 切换
            if (isLeft) {
                parent = parents.poll();
            }
        }
        return root;
    }
}
