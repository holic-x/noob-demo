package com.noob.algorithm.base.tree;

import sun.reflect.generics.tree.Tree;

/**
 * 二叉搜索树
 */
public class BinarySearchTree {

    /**
     * initTreeNode：初始化二叉树
     */
    public static TreeNode initTreeNode(){
        // 定义二叉树节点
        TreeNode root = new TreeNode(8);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(12);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(10);
        TreeNode node6 = new TreeNode(14);
        TreeNode node7 = new TreeNode(1);
        TreeNode node8 = new TreeNode(3);
        TreeNode node9 = new TreeNode(5);
        TreeNode node10 = new TreeNode(7);
        TreeNode node11 = new TreeNode(9);
        TreeNode node12 = new TreeNode(11);
        TreeNode node13 = new TreeNode(13);
        TreeNode node14 = new TreeNode(15);

        // 构建各个节点的关系（构建节点间的引用关系）
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node3.left = node7;
        node3.right = node8;
        node4.left = node9;
        node4.right = node10;
        node5.left = node11;
        node5.right = node12;
        node6.left = node13;
        node6.right = node14;

        // 返回根节点
        return root;
    }


    /**
     * 查找查找二叉树节点
     * @return
     */
    public static TreeNode search(int target,TreeNode root){
        // 定义检索指针从根节点开始
        TreeNode cur = root;
        // 循环查找
        while(cur != null){
            // 将当前节点值和目标值进行比较，如果当前值匹配则检索成功，否则进入左右子树校验
            if(target == cur.val){
                return cur;
            }else if(target < cur.val){
                cur = cur.left; // 进入左节点进行比较
            }else  if(target > cur.val){
                cur = cur.right; // 集合
            }
        }
        return null;
    }


    public static void main(String[] args) {
        BinarySearchTree bsh = new BinarySearchTree();
        TreeNode root = bsh.initTreeNode();
        TreeNode searchRes = bsh.search(5,root);
        System.out.println("查找结果：" + searchRes==null?"null":searchRes.val);
    }
}
