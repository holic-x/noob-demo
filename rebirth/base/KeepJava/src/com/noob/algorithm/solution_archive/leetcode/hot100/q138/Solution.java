package com.noob.algorithm.solution_archive.leetcode.hot100.q138;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 138.随机链表的复制
 */
public class Solution {
    /**
     * 随机指针的深拷贝
     * 由于随机指针的存在，无法直接通过一次遍历链表就获取到next、random的值
     * 思路：采用提前占位、延后装配的概念，先把链表的节点给初始化出来，然后再一步步去完善指针关系
     * 因此将问题转化为如何去构建指针关系，此处采用Map哈希表将原节点和对应的新节点分别作为key、value进行存储
     * 遍历这个Map的同时可以拿到对应的新节点并进行匹配，设置指针关系
     */
    public Node copyRandomList(Node head) {

        // 定义Map<OldNode,NewNode>存储老节点和新节点的映射关系，通过这个map就能获取对应的联系
        Map<Node,Node> map = new HashMap<Node,Node>();
        // 1.循环遍历链表，将老节点和对应其在新链表的新节点关联起来
        Node cur = head;
        while(cur!=null){
            // 创建新节点，此处只是预创节点，还没有建立指针关联
            Node newNode = new Node(cur.val);
            // 构建节点关联
            map.put(cur,newNode);
            // 构建完成，指针后移
            cur = cur.next;
        }

        // 2.上述新链表的节点都预创完成，则此处进一步构建节点的指针联系（通过遍历Map进行构建）
        Set<Node> oldNodeSet = map.keySet();
        Iterator<Node> oldNodeItr = oldNodeSet.iterator();
        while(oldNodeItr.hasNext()){
            // 获取老节点在新链表中对应的新节点，填充其next、random
            Node oldNode = oldNodeItr.next();
            Node newNode = map.get(oldNode); // 老节点对应的新节点就是key对应的value
            newNode.next = map.get(oldNode.next); // 类似地，设定新节点的next，其next就是指向原老节点指向的next作为key对应的value
            newNode.random = map.get(oldNode.random); // 以此类推
        }

        // 返回响应结果
        return map.get(head);
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