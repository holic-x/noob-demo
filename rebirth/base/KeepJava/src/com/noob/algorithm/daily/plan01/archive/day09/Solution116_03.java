package com.noob.algorithm.daily.plan01.archive.day09;

/**
 * 🟡 116 填充每个节点的下一个指针
 */
public class Solution116_03 {

    /**
     * DFS 思路：递归处理当前节点的左右子节点的next指针
     */
    public Node connect(Node root) {
        dfs(root);
        return root;
    }

    public void dfs(Node node) {
        // 递归出口
        if (node == null) {
            return;
        }

        // 分别处理左、右节点的next指针
        if (node.left != null) {
            node.left.next = node.right; // 当前节点的左节点的next指针指向node.right
        }
        if (node.right != null) {
            if (node.next != null) { // 需要处理右节点的next指针的情况，看node.next是否存在，如果不存在也不需要处理
                node.right.next = node.next.left; // 当前节点的右节点的next指着指向node.next.left
            }
        }

        // 递归处理
        dfs(node.left);
        dfs(node.right);
    }
}
