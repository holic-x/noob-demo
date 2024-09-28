package com.noob.algorithm.leetcode.q287;

/**
 * 287.寻找重复数
 * 思路：参考链表的快慢指针思路
 */
public class Solution {


    public int testNums(int[] nums) {

        // point 指针理解
        int point = 0;
        while(true){
            System.out.println(point);
            point = nums[point];
        }



    }

    public static void main(String[] args) {
        int[] nums = {3,1,4,2};
        Solution solution = new Solution();
        System.out.println(solution.testNums(nums));
    }


    public int findDuplicate(int[] nums) {

        // 定义快、慢指针
        int slow = 0,fast = 0;
        slow = nums[slow]; // 对应slow = slow.next （慢指针先走一步）
        fast = nums[nums[fast]];// 对应fast = fast.next.next (快指针先走两步)

        // 已知会存在环，因此此处是寻找环的入口
        while (fast != slow) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        // 定义finder指针和slow一起走，他们必定会在入口处相遇
        int finder=0;
        while(finder!=slow){
            finder = nums[finder];
            slow = nums[slow];
        }
        return slow; // finder
    }


}
