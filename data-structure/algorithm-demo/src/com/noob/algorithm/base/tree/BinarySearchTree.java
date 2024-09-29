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
     * 查找二叉树节点
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
                cur = cur.right; // 进入右节点进行比较
            }
        }
        return null;
    }


    /**
     * 插入节点
     * @return
     */
    public static TreeNode insert(int target,TreeNode root){
        // 1.判断根节点是否为null，如果为NULL直接新建节点即可。根节点不为NULL，检索节点插入位置，然后执行插入操作
        if(root == null){
            return new TreeNode(target);
        }

        // 2.查找插入位置（如果存在重复节点，则不执行任何操作直接返回）
        // 定义检索指针从根节点开始,pre保存上一轮循环的节点
        TreeNode cur = root, pre=null;
        // 循环查找
        while(cur != null){
            // 将当前节点值和目标值进行比较，如果当前值匹配则检索成功，否则进入左右子树校验
            if(target == cur.val){
                return root; // 节点重复，不执行任何操作，直接返回原来的root
            }
            // 确认插入位置
            pre = cur; // 保存插入位置的父节点信息
            if(target < cur.val){
                cur = cur.left; // 进入左节点进行比较
            }else  if(target > cur.val){
                cur = cur.right; // 进入右节点进行比较
            }
        }

        // 3.新建节点进行插入（此时以pre作为父节点进行插入）
        TreeNode node = new TreeNode(target);
        // 根据节点值进行判断插入位置
        if(pre.val<target){
            pre.right = node; // 插入左边
        }else{
            pre.left = node; // 插入右边
        }
        return root;
    }


    // 删除节点（找到节点位置，然后根据节点的度分情况讨论进行删除）
    public static void remove(int target,TreeNode root){
        // 判断树是否为空
        if(root==null){
            return; // 树为空，不执行任何操作
        }

        // 1.树不为空，查找删除节点
        TreeNode cur = root,pre=null;
        while(cur!=null) {
            if (cur.val == target) {
                break; // 位置定位成功，直接跳出循环，cur指向当前待删除节点位置
            }
            // 记录上一轮循环的cur节点位置
            pre = cur;
            // 根据值判断从左子树还是右子树进行查找
            if (cur.val > target) {
                cur = cur.left; // 待删除节点在cur的左子树
            } else if (cur.val < target) {
                cur = cur.right; // 待删除节点在cur的右子树
            }
        }

        // 判断待删除节点是否存在，如果不存在则不执行任何操作
        if(cur==null){
            return;
        }

        // 待删除节点存在，则根据待删除节点的度分情况讨论进行操作
        


    }





    public static void main(String[] args) {
        BinarySearchTree bsh = new BinarySearchTree();
        TreeNode root = bsh.initTreeNode();
        TreeNode searchRes = bsh.search(5,root);
        System.out.println("查找结果：" + searchRes==null?"null":searchRes.val);

        // 模拟插入操作
        bsh.insert(16,root);
        TreeNode res = bsh.search(16,root);
        System.out.println("查找结果：" + res==null?"null":res.val);
    }
}
