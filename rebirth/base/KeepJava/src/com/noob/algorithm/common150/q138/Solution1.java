package com.noob.algorithm.common150.q138;


import java.util.HashMap;
import java.util.Map;

/**
 * 138 随机链表的复制
 */
public class Solution1 {
    
    public Node copyRandomList(Node head) {

        // 定义虚拟头节点
        Node dummy = new Node(-1);
        Node point = dummy; // 定义新链表的指针

        // 1.提前创建新节点，构建oldNode、newNode 的映射关系
        Map<Node,Node> map = new HashMap<>();
        Node cur = head; // 定义cur指针遍历head原始链表
        while(cur!=null){
            // 创建新节点
            Node newNode = new Node(cur.val);
            // 构建映射关系
            map.put(cur,newNode);
            // 遍历指针后移
            cur = cur.next;
        }

        // 2.构建新链表的节点关系
        Node pcur = head;
        while(pcur!=null){
            // 根据映射关系构建节点联系（设定next、random）
            Node newNode = map.get(pcur);
            newNode.next = map.get(pcur.next);
            newNode.random = map.get(pcur.random);
            // 遍历指针后移
            pcur = pcur.next;

            // 将新节点加入新链表
            point.next = newNode;
            point = point.next;
        }

        // 返回结果
        return dummy.next;
    }
}


// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;
    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}