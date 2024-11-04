package com.noob.algorithm.common150.tree;

import com.noob.algorithm.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 非递归方式实现（借助栈辅助）栈（先进后出）
 */
public class DFSSearchByStack {

    // 前序遍历：DLR
    public List<Integer> DFSStackPreOrder(TreeNode root){
        // 空树判断
        if(root==null){
            return new ArrayList<>();
        }

        // 存储遍历结果
        List<Integer> list = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>(); // 定义辅助栈
        stack.push(root); // 根节点入栈

        // 遍历栈中元素，直到栈为空
        while(!stack.isEmpty()){
            // 根节点先访问到，因此先出栈
            TreeNode node = stack.pop();
            // right 节点最后访问到，先入栈
            if(node.right!=null){
                stack.push(node.right);
            }
            // 随后处理left
            if(node.left!=null){
                stack.push(node.left);
            }
            // 处理数据
            list.add(node.val);
        }

        // 返回结果
        return list;

    }


    public static void DFSStackInOrder111(TreeNode root){
        //left root  right的遍历顺序
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty()||root!=null){
            if(root!=null){
                //当前子树左节点不为空的话，
                //先将左节点入栈
                stack.push(root);
                root = root.left;
            }else{
                //当前子树左节点为空，
                //说明遍历到了最左边了，
                //要开始遍历子节点的有右子树了
                root = stack.pop();//出栈，拿到的是子节点的最左边
                System.out.println(root.val);//处理数据
                root = root.right;//把root设置成右子树，开始遍历右子树
            }
        }
    }




    // 中序遍历：LDR
    public List<Integer> DFSStackInOrder(TreeNode root){
        // 存储遍历结果
        List<Integer> list = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>(); // 定义辅助栈

        // 遍历栈中元素，直到栈为空
        while(!stack.isEmpty() || root!=null){
             if(root!=null){

             }
        }

        // 返回结果
        return list;

    }


    public static void DFSStackPostOrder(TreeNode root){
        //left right  root的遍历顺序
        if(root==null){
            return;
        }
        //后续遍历需要借助一个链表来先保存结果
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            //root是最后访问到的，
            // 所以需要每次都在链表头节点插入，
            // 这样最后root就会排在链表尾部
            result.add(0,root.val);
            if(root.left!=null){
                stack.push(root.left);
            }
            if(root.right!=null){
                stack.push(root.right);
            }
            root = stack.pop();
        }
        //到这里，链表里的数据就是最后结果了
        System.out.println(result);
    }




}
