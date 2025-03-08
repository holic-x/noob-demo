package com.noob.algorithm.plan_archive.plan02.hot100.day13.sort;

import com.noob.algorithm.solution_archive.dmsxl.util.PrintUtil;

/**
 * 排序算法核心思路：合并排序
 */
public class MergeSort {

    public static void main(String[] args) {
        // int[] nums = new int[]{1, 3, -2, 5, 6};
        int[] nums = new int[]{1, 6, 3, 2, 5, 4};


        // 执行排序算法
        MergeSort sort = new MergeSort();
        sort.merge_sort(nums);

        // 输出排序后的结果
        PrintUtil.print(nums);
    }


    // 排序算法：
    private void merge_sort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
    }


    // 合并排序核心算法
    private void mergeSort(int[] nums, int left, int right) {
        // 递归出口（终止条件）：当数组长度为1则退出
        if (left >= right) {
            return;
        }

        // ① 划分阶段（寻找中点进行划分）
        int mid = left + (right - left) / 2;
        // ② 递归处理左、右数组
        mergeSort(nums, left, mid); // 左数组：[left,mid]
        mergeSort(nums, mid + 1, right); // 右数组：[mid+1,right] / （mid,right]
        // ③ 合并阶段(合并两个有序数组)
        merge(nums, left, mid, right);
    }

    /*
    private void merge(int[] nums, int left, int mid, int right) {
        // 创建临时数组存储合并后的元素
        int n = right - left + 1;
        int[] merge = new int[n]; // 数组长度为[left,right]区间长度
        // 合并两个有序数组：leftNums:[left,mid]   rightNums:[mid+1,right]
        int cur = 0; // 用于指向合并后数组的填充位置
        int pl = left; // 用于指向leftNums的遍历位置
        int pr = mid + 1; // 用于指向rightNums的遍历位置
        while (pl <= mid && pr <= right) {
            // 选择较小的数进行填充
            if (nums[pl] <= nums[pr]) {
                merge[cur++] = nums[pl++]; // 填充元素，两个指针后移
            } else {
                merge[cur++] = nums[pr++]; // 填充元素，两个指针后移
            }
        }
        // 处理尾部
        while (pl <= mid) {
            merge[cur++] = nums[pl++]; // 填充元素，两个指针后移
        }
        while (pr <= right) {
            merge[cur++] = nums[pr++]; // 填充元素，两个指针后移
        }

        // 回填数组（将合并后的数组元素重新填充到nums）
        for (int i = 0; i < n; i++) {
            nums[left + i] = merge[i];
        }
    }
     */


    private void merge(int[] nums, int left, int mid, int right) {
        // 创建临时数组存储合并后的元素
        int n = right - left + 1;
        int[] merge = new int[n]; // 数组长度为[left,right]区间长度
        // 合并两个有序数组：leftNums:[left,mid]   rightNums:[mid+1,right]
        int cur = 0; // 用于指向合并后数组的填充位置
        int pl = left; // 用于指向leftNums的遍历位置
        int pr = mid + 1; // 用于指向rightNums的遍历位置
        while (pl <= mid || pr <= right) { // 简化尾部处理
            int val1 = (pl <= mid) ? nums[pl] : Integer.MAX_VALUE; // 左数组指向元素
            int val2 = (pr <= right) ? nums[pr] : Integer.MAX_VALUE;// 右数组指向元素
            // 选择较小的数进行填充
            if (val1 <= val2) {
                merge[cur++] = nums[pl++]; // 填充元素，两个指针后移
            } else {
                merge[cur++] = nums[pr++]; // 填充元素，两个指针后移
            }
        }

        // 回填数组（将合并后的数组元素重新填充到nums）
        for (int i = 0; i < n; i++) {
            nums[left + i] = merge[i];
        }
    }

}
