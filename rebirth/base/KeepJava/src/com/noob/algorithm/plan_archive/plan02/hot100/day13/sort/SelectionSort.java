package com.noob.algorithm.plan_archive.plan02.hot100.day13.sort;

import com.noob.algorithm.solution_archive.dmsxl.util.PrintUtil;

/**
 * 选择排序：从前往后固定[已排序区间，待排序区间]
 * 每次从待排序区间中比较选出一个最小值固定到前面的位置
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 6, 3, 2, 5, 4};

        SelectionSort ss = new SelectionSort();
        // ss.selection_sort1(nums);
        ss.selection_sort2(nums);

        // 输出排序后的结果
        PrintUtil.print(nums);

    }

    // 选择排序（基础版本）
    private void selection_sort1(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) { // 外层i确定填充位置
            for (int j = i + 1; j < n; j++) { // 内层j寻找待排序区间中的min
                if (nums[j] < nums[i]) { // 出现了更小的数，进行交换
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

    // 选择排序(内层循环是需要从待排序区间中选择min，因此可通过一个参数存储min，进而避免无效的交换) 操作优化版本
    private void selection_sort2(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) { // 外层i确定填充位置
            int curMinIdx = i; // 初始化最小值的索引位置
            for (int j = i + 1; j < n; j++) { // 内层j寻找待排序区间中的min(记录索引和值)
                if (nums[j] < nums[curMinIdx]) {
                    // 出现了更小的值，更新索引
                    curMinIdx = j;
                }
            }
            // 当前轮次遍历完成，将检索到的min填充到指定位置(交换i与curMinIdx位置)
            if (i != curMinIdx) { // 如果curMinIdx为初始化值则说明不需要交换
                int temp = nums[i];
                nums[i] = nums[curMinIdx];
                nums[curMinIdx] = temp;
            }
        }
    }
}
