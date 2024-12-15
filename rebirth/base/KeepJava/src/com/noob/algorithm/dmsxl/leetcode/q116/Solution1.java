package com.noob.algorithm.dmsxl.leetcode.q116;

import com.noob.algorithm.dmsxl.baseStructure.tree.Node;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 116 填充每个节点的下一个右侧节点指针
 * 完美二叉树
 */
public class Solution1 {

    // 思路：封装每个节点的next指针
    public Node connect(Node root) {
        // 判断root为null的情况
        if(root==null){
            return null;
        }

        // 构建辅助队列
        Deque<Node> queue = new LinkedList<>();
        queue.offer(root); // 初始化队列

        // 遍历队列节点(层序遍历顺序：从上到下、从右到左)
        while(!queue.isEmpty()){
            // 分层遍历
            int curSize = queue.size();
            // 定义nextNode初始化为null（对于每一层都右一个初始化的nextNode节点）
            Node nextNode = null;
            for(int i=0;i<curSize;i++){
                Node cur = queue.poll();
                cur.next = nextNode; // 更新当前节点的nextNode

                // 如果存在子节点则入队（遍历顺序从右往左，因此入队顺序从右往左）
                if(cur.right !=null){
                    queue.add(cur.right);
                }
                if(cur.left !=null){
                    queue.add(cur.left);
                }

                // 当前节点遍历完成，更新nextNode（用于下个节点的next指向）
                nextNode = cur;
            }
        }

        // 返回节点
        return root;
    }
}


