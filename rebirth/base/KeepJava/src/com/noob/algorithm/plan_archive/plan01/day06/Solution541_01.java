package com.noob.algorithm.plan_archive.plan01.day06;

/**
 * 🟢 541 反转字符串II
 */
public class Solution541_01 {

    /**
     * 局部反转思路（限定反转范围，每一个轮次的起点终点）
     * 反转轮次：0,2k,4k,6k....(对于每个轮次，超出2k范围，看最终剩余的元素数量与k的关系，不足k全部反转，足k则反转前K)
     * 反转的起始范围：[i,end],此处end有两种情况讨论：
     * - ①在2k*n轮次范围限定中：[i,i+k-1]
     * - ②在2k*n轮次范围之后（剩余的字符）：end=min{i+k-1,len-1}
     * - 2.a 如果剩余字符个数>=k：[i,i+k-1]
     * - 2.b 如果剩余字符个数<k：[i,len-1]（考虑数组长度限定，此处是将剩余所有字符反转）
     */
    public String reverseStr(String s, int k) {
        int len = s.length();
        char[] arr = s.toCharArray();
        // 限定反转范围，局部反转字符串（将字符串转化为字符数组进行处理）
        for (int i = 0; i < len; i += 2 * k) { // 处理每一个轮次的字符串反转
            reverse(arr, i, Math.min(i + k - 1, len - 1));
        }
        // 将反转后的字符数组还原成字符串
        return new String(arr);
    }

    /**
     * 局部反转字符数组(原地反转)
     */
    public void reverse(char[] arr, int start, int end) {
        while (start <= end) {
            // 反转
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            // 指针移动
            start++;
            end--;
        }
    }
}
