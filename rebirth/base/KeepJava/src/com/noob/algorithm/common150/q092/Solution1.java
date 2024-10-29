package com.noob.algorithm.common150.q092;

import com.noob.algorithm.common150.base.ListNode;

/**
 * 092 反转链表II
 */
public class Solution1 {

    /**
     * 分段处理：
     * 第1段：[0,left-1] 正常遍历
     * 第2段：[left,right] 反转
     * 第3段：[right+1,end] 拼接
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 定义虚拟头节点
        ListNode dummy = new ListNode(-1, head); // 将head拼接过来构成一个新链表，对这个新链表进行区间反转操作：等价于ListNode dummy = new ListNode(-1); dummy.next = head;
        ListNode pd = dummy; // pd指针指向新链表的头节点

        ListNode cur = head; // cur指针指向head链表的头节点

        /**
         * 第1段：正常遍历(p、cur向前移动，到达要进行反转的左区间)
         * 遍历完成 cur 指向的节点就是是要翻转的节点
         * 遍历完成 pd 的下一个节点指向的是要翻转的节点（因为dummy多了个虚拟头结点）
         */
        for (int i = 0; i < left - 1; i++) {
            pd = pd.next;
            cur = cur.next;
        }

        /**
         * 第2段：反转这个范围区间的链表节点
         */
        for (int i = 0; i < right - left; i++) {
            ListNode temp = cur.next ; // 保存当前需要翻转的节点后面的节点
            
        }


    }

}
