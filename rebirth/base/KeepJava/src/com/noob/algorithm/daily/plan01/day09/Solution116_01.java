package com.noob.algorithm.daily.plan01.day09;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟡 116 填充每个节点的下一个指针
 */
public class Solution116_01 {

    // 基于层序遍历BFS思路：每一层都从右往左开始遍历，将上一个遍历的节点记为pre(初始化为null)，则上一个节点会作为下一个遍历节点的next
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        // 构建辅助队列
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root); // 初始化队列
        // 分层遍历节点
        while (!queue.isEmpty()) {
            Node pre = null; // 初始化pre指针，指向每一层当前遍历节点的上一个节点（按层处理，不是多层共用）
            int cnt = queue.size(); // 记录当层遍历的节点个数
            while (cnt-- > 0) {
                // 取出节点
                Node cur = queue.poll();

                // 设置节点next指向上一个节点
                cur.next = pre;

                // 每一层的遍历顺序是从右到左
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }

                // 遍历完成，更新pre指针
                pre = cur;
            }
        }

        // 返回更新后的树
        return root;
    }
}

// 自定义Node结构
class Node {
    int val;
    Node left;
    Node right;
    Node next;
}
