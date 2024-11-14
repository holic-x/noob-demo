package com.noob.algorithm.dmsxl.leetcode.q429;

import com.noob.algorithm.dmsxl.baseStructure.NTreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 429 N 叉树的遍历
 */
public class Solution1 {

    public List<List<Integer>> levelOrder(NTreeNode root) {
        // 判断root是否为null
        if (root == null) {
            return new ArrayList<>();
        }

        // 定义遍历结果集
        List<List<Integer>> res = new ArrayList<>();

        // 构建辅助队列
        Deque<NTreeNode> queue = new LinkedList<>();
        queue.offer(root); // 初始化队列

        // 遍历队列元素
        while (!queue.isEmpty()) {
            // 遍历当层元素
            List<Integer> curList = new ArrayList<>();
            // 记录当层元素个数
            int curSize = queue.size();
            // 分层遍历
            for(int i=0;i<curSize;i++){
                // 取出队列元素
                NTreeNode cur = queue.poll();
                curList.add(cur.val);

                // 如果子节点不为空则入队
                List<NTreeNode> children = cur.children;
                if(!children.isEmpty()){
                    for (NTreeNode child : children){
                        if(child!=null){
                            queue.offer(child);
                        }
                    }
                }
            }
            // 当层遍历完成，封装结果集
            res.add(curList);
        }

        // 返回响应结果
        return res;
    }

}
