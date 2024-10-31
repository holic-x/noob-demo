package com.noob.algorithm.common150.q011;

/**
 * 11.盛水最多的容器
 */
public class Solution1 {
    /**
     * 双指针思路：
     * 盛水公式：area = min(h[left],h[right])*(right-left)
     * 因为right-left会随着指针移动变小，因此考虑怎样让min(h[left],h[right])尽量大
     * =>移动短边：移动短边或许可以拿到更大的值，因此采用这个思路切入
     */
    public int maxArea(int[] height) {
        // 定义响应结果
        int maxArea = Integer.MIN_VALUE;

        // 定义双指针

        int left = 0, right = height.length - 1;

        // 遍历数组，计算maxArea，指针相遇遍历结束
        while(left < right) {
            // 计算当前容器盛水，更新最大值
            int curArea = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, curArea);
            // 计算完成，指针继续移动（移动短边，以尽可能获取更大的值）
            if(height[left] < height[right]) {
                left++;
            }else if(height[left] >= height[right]) {
                right--;
            }
        }

        // 返回结果
        return maxArea;
    }

}
