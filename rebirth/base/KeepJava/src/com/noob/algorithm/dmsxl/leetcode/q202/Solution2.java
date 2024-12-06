package com.noob.algorithm.dmsxl.leetcode.q202;

/**
 * 202 快乐数
 */
public class Solution2 {

    public boolean isHappy(int n) {
        // 转化为链表是否存在环的思路（龟兔赛跑）
        int slow = n;
        int fast = n;
        while (fast != 1) {
            slow = calN(slow);
            fast = calN(calN(fast));
            if (slow == fast) {
                return false; // 指针相遇说明存在环，即非快乐数返回false
            }
        }
        // 跳出循环说明fast先走到终点1
        return true;
    }

    public int calN(int n) {
        // 计算 n (每个位数的平方之和)
        int calN = 0;
        while (n != 0) {
            // 获取个位上的数字，拿到平方和
            calN += (n % 10) * (n % 10);
            n = n / 10; // 数位分离继续计算下一个位置
        }
        // 返回计算结果
        return calN;
    }

    //    public static void main(String[] args) {
//        Solution704 solution1 = new Solution704();
//        solution1.isHappy(19);
//    }

}
