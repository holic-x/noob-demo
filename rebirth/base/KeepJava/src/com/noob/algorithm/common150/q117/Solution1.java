package com.noob.algorithm.common150.q117;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 117 填充每个节点的下一个右侧节点指针II
 */
public class Solution1 {


    public Node connect(Node root) {
        if (root == null) return root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            Node tempNode = null;
            for (int i = 0; i < size; i++){
                Node curr = queue.poll();
                if (curr.right != null) queue.add(curr.right);
                if (curr.left != null) queue.add(curr.left);
                curr.next = tempNode;
                tempNode = curr;
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

        Solution1 solution1 = new Solution1();
        solution1.connect(root);
    }

}


/**
 * Node 节点定义
 */
class Node {
    int val;
    Node left; // 左节点
    Node right; // 右节点
    Node next; // next指针

    Node(int val){
        this.val = val;
    }
}