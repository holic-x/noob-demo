package com.noob.algorithm.dmsxl.leetcode.q142;

import com.noob.algorithm.dmsxl.baseStructure.tree.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 141 环形链表
 */
public class Solution2 {
    // 哈希表：如果存在环返回入环的入口，不存在返回null
    public ListNode detectCycle(ListNode head) {
        if(head==null){
            return null;
        }
        // List 存储已遍历元素(需确保有序性)
        Set<ListNode> visited = new HashSet<>();
        // 遍历链表，判断cur.next是否已经被访问过
        ListNode cur = head;
        while(cur!=null){
            if(visited.contains(cur.next)){
                return cur.next; // 存在环说明指向同一个节点，因此此处直接返回这个入口即可
            }
            visited.add(cur); // 将当前指针放入visited集合
            cur = cur.next; // 指针后移，继续下一个元素判断
        }
        // 所有节点遍历完成，说明不存在环
        return null;
    }
}
