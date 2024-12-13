package com.noob.algorithm.dmsxl.leetcode.q117;

import com.noob.algorithm.dmsxl.baseStructure.tree.Node;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 117 填充每个节点的下一个右侧节点指针
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


            for(int i=0;i<curSize;i++){
                // 遍历当前节点
                Node cur = queue.poll();
                // 更新当前节点的next（继续从队列中读取下一个元素，不存在则null）
                if(i<curSize-1){ // 只需要封装当层的前k-1个节点的next，当层最后一个节点默认为null(基于这个设定也可控制NPE)
                    cur.next = queue.peek();
                }

                // 如果存在子节点则入队（按照正常层次遍历顺序：left、right）
                if(cur.left !=null){
                    queue.add(cur.left);
                }
                if(cur.right !=null){
                    queue.add(cur.right);
                }

            }
        }

        // 返回节点
        return root;
    }
}
