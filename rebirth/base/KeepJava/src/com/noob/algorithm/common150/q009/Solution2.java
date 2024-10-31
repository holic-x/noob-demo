package com.noob.algorithm.common150.q009;

/**
 * 009 回文数
 */
public class Solution2 {

    /**
     * 回文数：逆序都是一样的数字（回归到和回文字符串判断的思路）
     * 正序逆序遍历序列一致、双指针遍历等
     */
    public boolean isPalindrome(int x) {
        String xstr = String.valueOf(x);
        // 定义双指针进行遍历
        int start = 0,end = xstr.length()-1;
        // 遍历元素，指针相遇则循环结束
        while(start<end){
            // 判断指针指向元素是否相同
            if(xstr.charAt(start)!=xstr.charAt(end)){
                return false;
            }
            // 指针后移，继续比较下个位置
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution2 solution1 = new Solution2();
        System.out.println(solution1.isPalindrome(121));
    }
}
