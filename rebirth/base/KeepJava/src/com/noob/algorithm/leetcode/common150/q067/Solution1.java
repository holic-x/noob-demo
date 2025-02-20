package com.noob.algorithm.leetcode.common150.q067;

/**
 * 067 二进制求和
 */
public class Solution1 {

    /**
     * 二进制求和
     */
    public String addBinary(String a, String b) {
        int aLen = a.length(), bLen = b.length();

        StringBuffer sb = new StringBuffer();

        int carry = 0;
        int aPoint = aLen - 1, bPoint = bLen - 1;
        while (aPoint >= 0 || bPoint >= 0) {
            int curA = aPoint >= 0 ? a.charAt(aPoint) - '0' : 0; // 注意字符转数字的处理
            int curB = bPoint >= 0 ? b.charAt(bPoint) - '0' : 0;
            int count = curA + curB + carry;
            sb.append(count > 1 ? count - 2 : count); // 如果count超出1则当前位置要进位，且设定进位

            // 更新carry
            carry = count > 1 ? 1 : 0;

            // 遍历完成，指针移动
            aPoint--;
            bPoint--;
        }

        // 将最后的进位也加上
        if (carry > 0) {
            sb.append(carry);
        }

        // 返回逆序
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        solution1.addBinary("1111", "1111");
    }

}
