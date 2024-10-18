package com.noob.algorithm.hot100.q021;

import com.noob.algorithm.hot100.q160.ListNode;

/**
 * 021 合并两个有序链表
 */
public class Solution {

    /**
     * 合并两个有序链表，本质上就是依次比较两个链表元素大小，然后进行插入操作
     * 两个链表本身有序，因此构建一个链表存储结果，依次插入满足条件的数据即可
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        // 定义链表头
        ListNode dummy = new ListNode(0);
        // 定义新链表的指针
        ListNode cur =dummy;

        // 定义两个链表的遍历指针
        ListNode pointer1 = list1;
        ListNode pointer2 = list2;

        /**
         * 依次遍历链表元素，比较链表元素
         * （由于未知链表长度，因此在同时遍历的时候如果有一个链表遍历到了尾节点就停止遍历，将next指针指向另一个遍历完的链表位置即可）
         */
        while(pointer1 != null && pointer2 != null) {
            // 如果当前链表1的元素小于链表2的元素，则指针指向链表1元素
            if(pointer1.val < pointer2.val) {
                // 将链表1元素加入新链表
                ListNode temp = new ListNode(pointer1.val);
                cur.next = temp;
                // 链表1指针和新链表指针后移
                pointer1 = pointer1.next;
                cur = cur.next;
            }else{
                ListNode temp = new ListNode(pointer2.val);
                cur.next = temp;
                // 链表2指针和新链表指针后移
                pointer2 = pointer2.next;
                cur = cur.next;
            }
        }

        // 跳出循环说明至少其中一个链表遍历到尾部，此时判断哪个为空说明该链表遍历完成，只需要将新链表的next指向剩下的即可
        if(pointer1 != null) {
            cur.next = pointer1;
        }
        if(pointer2 != null) {
            cur.next = pointer2;
        }

        // 返回新链表
        return dummy.next;

    }

}
