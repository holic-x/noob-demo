package com.noob.algorithm.plan_archive.plan01.day09;

/**
 * 🟡 116 填充每个节点的下一个指针
 */
public class Solution116_02 {

    /**
     * DFS 思路：递归处理当前节点的左右子节点的next指针
     */
    public Node connect(Node root) {
        dfs(root);
        return root;
    }

    public void dfs(Node node) {
        if (node == null) {
            return;
        }

        // 如果节点的左节点为null则退出（基于完美二叉树特性，节点从左到右填充，如果左节点为空，则该左节点右侧不会存在节点，也就是说不需要处理next）
        if (node.left == null) {
            return;
        }

        // 分别处理左、右节点的next指针
        if (node.left != null) {
            node.left.next = node.right; // 当前节点的左节点的next指针指向node.right
        }
        if (node.next != null) { // 需要处理右节点的next指针的情况，看node.next是否存在，如果不存在也不需要处理
            node.right.next = node.next.left; // 当前节点的右节点的next指着指向node.next.left
        }

        // 递归处理
        dfs(node.left);
        dfs(node.right);
    }
}
