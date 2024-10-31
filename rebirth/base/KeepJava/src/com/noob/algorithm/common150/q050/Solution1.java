package com.noob.algorithm.common150.q050;

/**
 * 050 实现Pow（x,n）即x的n次幂
 */
public class Solution1 {
    public double myPow(double x, int n) {
        // 定义操作结果
        double res = 1;
        if(n==0){
            return 1;
        }else if(n>0){
            // 如果n>0，则是n个x累乘
            for(int i=1;i<=n;i++){
                res *= x;
            }
        }else {
            // 如果n<0,则为x的倒数的累乘
            x = 1/x;
            for(int i=1;i<= (-1)*n ;i++){ // 将n切为整数
                res *= x;
            }
        }
        return res;
    }

}
