package com.noob.algorithm.common150.tree;

import com.noob.algorithm.common150.base.TreeNode;

import java.util.*;

public class BFSSearch {

    public List<Integer> bfs(TreeNode root){
        // 定义列表存储结果集
        List<Integer> res = new ArrayList<>();
        // 借助队列辅助存储
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root); // 初始化
        while(!queue.isEmpty()){
            // 取出节点
            TreeNode cur = queue.poll();
            res.add(cur.val);
            // 判断左右节点
            if(cur.left!=null){
                queue.offer(cur.left);
            }
            if(cur.right!=null){
                queue.offer(cur.right);
            }
        }
        // 返回结果集
        return res;
    }

    // 分层BFS遍历
    public List<List<Integer>> bfsByLevel(TreeNode root){
        // 空节点校验
        if(root==null){
            return new ArrayList<>();
        }

        // 定义列表存储结果集
        List<List<Integer>> res = new ArrayList<>();
        // 借助队列辅助存储
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root); // 初始化
        while(!queue.isEmpty()){
            // 获取当前队列的元素个数（用作分层统计）
            int n = queue.size();
            // 定义当层结果集
            List<Integer> curList = new ArrayList<>();
            // 分层统计
            while(n>0){
                // 取出节点
                TreeNode cur = queue.poll();
                curList.add(cur.val);

                // 判断左右节点
                if(cur.left!=null){
                    queue.offer(cur.left);
                }
                if(cur.right!=null){
                    queue.offer(cur.right);
                }
                n--; // 当n减为0说明当前轮次遍历结束，进入下一层
            }
            // 每层结果遍历完成则加入结果集，随后进入下一层遍历
            res.add(curList);
        }
        // 返回结果集
        return res;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);

        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;

        BFSSearch bfsSearch = new BFSSearch();
        List<Integer> res = bfsSearch.bfs(root);
        System.out.println(res);

        List<List<Integer>> res1 = bfsSearch.bfsByLevel(root);
        System.out.println(res1);
    }

}
