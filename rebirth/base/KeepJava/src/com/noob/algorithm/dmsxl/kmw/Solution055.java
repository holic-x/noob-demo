package com.noob.algorithm.dmsxl.kmw;

/**
 * 055 右旋字符串
 */
public class Solution055 {

    // 思路1：指定目标字符串和整数K，将后K位的数字移动到头部（空间复杂度O(n)）
    public String rightRotate1(String target, int k) {
        // 定义结果字符串
        StringBuffer res = new StringBuffer();
        // 先拼接后K位
        int len = target.length();
        for (int i = len - k; i < len; i++) {
            res.append(target.charAt(i));
        }
        // 再拼接前面的内容
        for (int i = 0; i < len - k; i++) {
            res.append(target.charAt(i));
        }
        // 返回结果
        return res.toString();
    }


    // 思路2：局部反转+真题反转（空间复杂度:O(n)）
    public String rightRotate(String target, int k) {
        char[] targetArr = target.toCharArray();
        int len = target.length();
        // 局部反转
        reverseStr(targetArr, 0, len - k - 1);
        reverseStr(targetArr, len - k, len - 1);
        // 整体反转
        reverseStr(targetArr, 0, len - 1);
        // 返回反转后的字符串数据
        return new String(targetArr);
    }

    // 自定义反转方法(原地反转数组元素):[start,end]闭区间
    public void reverseStr(char[] targetArr, int start, int end) {
        while (start < end) {
            char temp = targetArr[start];
            targetArr[start] = targetArr[end];
            targetArr[end] = temp;
            // 交换完成，指针靠拢
            start++;
            end--;
        }
    }


    // 测试
    public static void main(String[] args) {
        Solution055 solution = new Solution055();
        String res = solution.rightRotate("abcdefg", 2);
        System.out.println(res);
    }

}
