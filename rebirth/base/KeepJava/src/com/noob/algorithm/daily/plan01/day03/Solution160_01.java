package com.noob.algorithm.daily.plan01.day03;

import com.noob.algorithm.dmsxl.baseStructure.tree.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 160 链表相交
 */
public class Solution160_01 {

    // 思路：哈希法
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 构建辅助集合存储headA中的元素
        List<ListNode> list = new ArrayList<>();
        // 遍历链表headA
        ListNode pa = headA;
        while(pa!=null){
            list.add(pa);
            pa = pa.next;
        }

        // 遍历链表headB
        ListNode pb = headB;
        while(pb!=null){
            // 判断元素是否在集合中出现，如果出现则说明存在交点
            if(list.contains(pb)){
                return pb;
            }
            pb = pb.next;
        }

        return null;
    }
}
