package com.noob.algorithm.leetcode.common150.q141;

import com.noob.algorithm.leetcode.common150.base.ListNode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 141 环形链表
 */
public class Solution1 {

    /**
     * 判断链表中是否存在环
     * 思路：哈希表
     */
    public boolean hasCycle(ListNode head) {
        // 定义集合存储链表元素，如果集合中存在重复元素则说明链表中存在环
        Set<Integer> set = new HashSet<>();

        ListNode point = head ; // 定义遍历指针
        while(point!=null){
            if(set.contains(point.val)){
                // 集合中已存在该元素，说明存在环
                return true;
            }
            // 将元素加入集合，指针后移
            set.add(point.val);
            point = point.next;
        }
        return false;
    }
}
