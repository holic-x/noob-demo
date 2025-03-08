package com.noob.algorithm.daily.search;

/**
 * 二分查找
 */
public class BinarySearch {

    /**
     * 闭区间：[left,right]
     */
    public static int search_1(int[] nums, int target) {
        // 定义指针
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            // 计算中点
            int mid = left + (right - left) / 2;
            // 根据nums[mid] 与 target 的取值进行缩圈
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                // 目标值在右侧，则左边界缩圈
                left = mid + 1;
            } else if (nums[mid] > target) {
                // 目标值在左侧，则右边界缩圈
                right = mid - 1;
            }
        }
        // 没有找到目标元素
        return -1;
    }


    /**
     * 左闭右开区间：[left,right)
     */
    public static int search_2(int[] nums, int target) {
        // 定义指针
        int left = 0, right = nums.length;
        while (left < right) {
            // 计算中点
            int mid = left + (right - left) / 2;
            // 比较 nums[mid] 与 target
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                // 目标值在右侧，左边界缩圈
                left = mid + 1;
            } else if (nums[mid] > target) {
                // 目标值在左侧，右边界缩圈
                right = mid;
            }
        }
        // 没有找到目标元素
        return -1;
    }

    /**
     * 开区间：(left,right)
     */
    public static int search_3(int[] nums, int target) {
        // 定义指针
        int left = -1, right = nums.length;
        while (left + 1 < right) {
            // 计算中点
            int mid = left + (right - left) / 2;
            // 比较 nums[mid] 与 target
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                // 目标值在右侧，左边界缩圈
                left = mid;
            } else if (nums[mid] > target) {
                // 目标值在左侧，右边界缩圈
                right = mid;
            }
        }
        // 没有找到目标元素
        return -1;
    }

    public static void main(String[] args) {
        // 给定有序数组
        int[] arr = new int[]{1, 2, 3, 4, 5, 7, 8, 9};
        System.out.println(search_1(arr, 6));
        System.out.println(search_1(arr, 3));
        System.out.println("----------------------");
        System.out.println(search_2(arr, 6));
        System.out.println(search_2(arr, 3));
        System.out.println("----------------------");
        System.out.println(search_3(arr, 6));
        System.out.println(search_3(arr, 3));
        System.out.println("----------------------");
    }

}
