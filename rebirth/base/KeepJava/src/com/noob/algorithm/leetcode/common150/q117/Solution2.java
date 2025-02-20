package com.noob.algorithm.leetcode.common150.q117;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 117 填充每个节点的下一个右侧节点指针II
 */
public class Solution2 {

    /**
     * 基于层序遍历的思路实现
     * root、right、left（从上到下，从右到左的方向进行遍历）
     */
    public Node connect(Node root) {
        // null判断
        if (root == null) {
            return root;
        }

        // 定义辅助队列存储遍历节点
        Deque<Node> queue = new LinkedList<>();
        queue.add(root); // 初始化队列
        // 层序遍历（从上到下，从右到左）
        while (!queue.isEmpty()) {
            int size = queue.size(); // 获取当前队列size（当层节点个数）
            Node tempNextNode = null; // 定义临时节点存储这个next
            for (int i = 0; i < size; i++) {
                // 遍历当层元素
                Node cur = queue.poll();
                // 将右、左节点分别先后加入队列
                if (cur.right != null) {
                    queue.add(cur.right); // 此处添加的是cur.right(不要犯常识性错误)
                }
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                /**
                 * 构建cur的指针关系（cur指向的是右边的节点）
                 * 如果入队是先右后左的话，那么上一个入队的元素会作为下一个节点的next
                 */
                cur.next = tempNextNode; // 上一个入队的元素会作为下一个节点的next（当前cur.next指向的就是上一个遍历节点）
                tempNextNode = cur; // 更新cur为tempNextNode，其会作为下一个遍历节点的next
            }
        }
        return root;
    }


    public static void main(String[] args) {
        Node root = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n7 = new Node(7);
        root.left = n2;
        root.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.right = n7;

        Solution2 s = new Solution2();
        s.connect(root);
    }

}

