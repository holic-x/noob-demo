package com.noob.algorithm.solution_archive.dmsxl.leetcode.q141;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 141 环形链表
 */
public class Solution2 {
    // 哈希表
    public boolean hasCycle(ListNode head) {
        if(head==null){
            return false;
        }
        // Set存储已遍历元素
        Set<ListNode> visited = new HashSet<>();
        // 遍历链表，判断cur.next是否已经被访问过
        ListNode cur = head;
        while(cur!=null){
            if(visited.contains(cur.next)){
                return true;
            }
            visited.add(cur); // 将当前指针放入visited集合
            cur = cur.next; // 指针后移，继续下一个元素判断
        }
        // 所有节点遍历完成，说明不存在环
        return false;
    }
}
