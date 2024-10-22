package com.noob.algorithm.common150.q080;

/**
 * 80 删除数组中重复的元素II（允许重复次数超出2的可以最多出现两次）
 */
public class Solution2 {

    /**
     * todo 画图
     * 因为给定数组有序，因此相同的元素是连续的
     * 定义快慢指针进行遍历：慢指针指向当前元素存储的位置（新数组）、快指针用于遍历时指向当前比较元素
     */
    public int removeDuplicates(int[] nums) {
        // 判断nums长度
        int len = nums.length;
        // 如果nums长度小于等于2则直接返回数组长度
        if(len<=2){
            return len;
        }

        // 如果nums长度大于2，则通过覆盖的方式来进行元素更新
        // 定义快、慢指针
        int slow = 2;
        int fast = 2;
        // 遍历数组元素确认条件进行覆盖,当快指针走完了说明操作完成
        while(fast<len){
            // 判断当前快指针指向元素和其前2个元素是否相同，如果相同则快指针继续前进
            if(nums[slow-2]==nums[fast]){
                fast ++;
            }else{
                // 如果不相同，则覆盖当前位置
                nums[slow] = nums[fast];
                slow ++;
                fast ++;
            }
        }

        return slow;
    }
}
