package com.noob.algorithm.leetcode.q199;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 199.二叉树的右视图
 */
public class Solution1 {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        list = showRight(root,list);
        Collections.reverse(list); // 反转
        return list;
    }

    public List<Integer> showRight(TreeNode node,List<Integer> list) {
        if(node == null){
            return list;
        }
        // 判断打印右节点
        showRight(node.right,list);
        list.add(node.val);
        return list;
    }

    /*
    public List<Integer> toBFS(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        list = bfs(root, list);
        return list;
    }

    public List<Integer> bfs(TreeNode node,List<Integer> list) {
        // 如果node为null不执行操作
        if(node==null){
            return list;
        }
        // 中序遍历：left->root->right
        bfs(node.left,list);
        list.add(node.val);
        bfs(node.right,list);
        // 返回list
        return list;
    }
     */

}

/* 二叉树节点类 */
class TreeNode {
    int val;         // 节点值
    TreeNode left;   // 左子节点引用
    TreeNode right;  // 右子节点引用

    TreeNode(int x) {
        val = x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

