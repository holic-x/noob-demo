package com.noob.algorithm.hot100.q283;

import java.util.Arrays;

/**
 * 283 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作
 */
public class Solution1 {


    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        Solution1 solution1 = new Solution1();
        solution1.moveZeroes1(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }


    /**
     * 双指针（同向）
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        // 右边存的是0元素，因此判断依据是当right走到n的位置表示遍历结束
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }


    /**
     * 双指针（相向） + 交换的思路
     * 因为不能用到额外的数组空间，因此此处采用双指针思路，左指针记录非0元素存储的位置；右指针记录0元素存储的位置
     * 如果左指针指向元素不为0则左指针继续向前，如果指向元素为0则和右指针所在位置元素进行交换，直到两个指针相遇
     * 需注意如果左右指针元素都为0的话，则右指针要继续往前（否则0 0交换没有意义了）
     * PS:此思路是用两个反方向的指针来做的，虽然思路ok，但是数组元素的顺序会被打乱
     * @param nums
     */
    public void moveZeroes2(int[] nums) {
        // 定义双指针
        int left = 0, right = nums.length - 1;
        // 指针相遇循环结束
        while (left <= right) {
            // 以左指针为基础判断，如果左指针元素为0需考虑交换（还需考虑右指针是否为0）
            if (nums[left] == 0) {
                // 判断右指针是否为0
                if (nums[right] == 0) {
                    right--; // 右指针为0，则右指针向前
                } else {
                    // 交换左右指针
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;
                    // 左右指针靠拢
                    left++;
                    right--;
                }
            } else {
                // 考虑左指针不为0的情况，则左指针继续往前即可
                left++;
            }
        }
    }


    /**
     * 暴力思路：一次循环遍历出所有非0元素，然后加入一个新数组（创建同样size的数组），记录元素为0的个数队尾补0（或者数组初始化本身就为0）
     * 需要用到额外的数组空间
     *
     * @param nums
     */
    public void moveZeroes1(int[] nums) {
        int[] newNums = new int[nums.length];
        // 循环遍历，将非0元素加入新数组
        int cur = 0; // cur 为新数组的指针
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                newNums[cur++] = nums[i];
            }
        }
        // 让nums指向新数组
        for (int i = 0; i < nums.length; i++) {
            nums[i] = newNums[i];
        }
    }
}
