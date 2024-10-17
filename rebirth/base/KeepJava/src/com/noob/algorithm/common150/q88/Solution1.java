package com.noob.algorithm.common150.q88;

/**
 * 88 合并两个有序数组
 */
public class Solution1 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 定义结果
        int[] res = new int[m + n];

        // 定义遍历指针分别对应nums1、nums2数组遍历
        int pointer1 = 0;
        int pointer2 = 0;
        int cur = 0 ; // 当前存储的位置
        // 在数组下标限制范围内进行遍历
        while(pointer1 < m && pointer2 < n) {
            // 判断nums1 和 nums2 当前指针位置元素大小，按升序进行排序
            if(nums1[pointer1] <= nums2[pointer2]) {
                // 选择小数：即选择nums1[pointer1]，指针后移
                res[cur++] = nums1[pointer1++];
            }else{
                // 选择小数：即选择nums2[pointer2]，指针后移
                res[cur++] = nums2[pointer2++];
            }
        }
        /**
         * 尾巴判断（上述循环中必然有一个数组先遍历完，此处则继续确认剩下的元素）
         * 如果是nums1还没遍历完，则需要把nums1的数补到res中
         * 如果是nums2还没遍历完，则需要把nums2的数补到res中
         */
        while(pointer1<m){
            res[cur++] = nums1[pointer1++];
        }
        while(pointer2<n){
            res[cur++] = nums2[pointer2++];
        }

        // nums1作为结果返回，此处封装结果
        for(int i = 0 ; i < res.length ; i++){
            nums1[i] = res[i];
        }
    }
}
