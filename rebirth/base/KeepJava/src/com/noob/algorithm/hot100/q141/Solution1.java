package com.noob.algorithm.hot100.q141;

import com.noob.algorithm.hot100.q160.ListNode;

import java.util.HashSet;

/**
 * 141 环形链表
 */
public class Solution1 {
    /**
     * 环形链表：说明遍历next的时候，元素已经被遍历过了，因此可以用哈希表标记已遍历的元素，判断当前要遍历的值是否已经被标记
     * 将问题转化为判断"重复元素概念"
     */
    public boolean hasCycle(ListNode head) {
        // 定义哈希表标记已遍历元素
        HashSet<Integer> set = new HashSet<>();

        // 遍历元素
        ListNode cur = head; // 定义指针
        while(cur!=null){
            // 判断当前遍历元素是否已被标记
            if(set.contains(cur.val)){
                // 如果已被标记说明存在环
                return true;
            }
            // 元素未被标记，则进行标记
            set.add(cur.val);
            cur = cur.next; // 指针后移
        }
        return false;
    }
}
