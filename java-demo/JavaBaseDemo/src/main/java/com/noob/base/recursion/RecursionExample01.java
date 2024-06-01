package com.noob.base.recursion;

/**
 * @ClassName Huh-x
 * @Description TODO
 * @Author Huh-x
 * @Date 2024 2024/4/29 8:48
 */
public class RecursionExample01 {

    public static void main(String[] args) {
        RecursionExample01 re  = new RecursionExample01();
        for (int i = 1; i <= 5; i++) {
            System.out.println("第"+i +"个数据：" + re.fibonacci(i) );
        }
    }

    /**
     * 斐波那契数列：
     * 1,2,3,5,8,13
     * 规律：
     * 1 =》 1     =》 1
     * 2 =》 2     =》 2
     * 3 =》 1+2   =》 3
     * 4 =》 2+3   =》 5
     * 5 =》 3+5   =》 8
     */
    public int fibonacci(int n) {
        // 设置递归退出条件
        if(n == 1 || n == 2) {
            return n;
        }else if(n>=3){
            // 前两个数之和
            return fibonacci(n-1) + fibonacci(n-2);
        }else{
            // 其他数据输入判断异常
            System.out.println("输入数据异常");
            return -1;
        }
    }
}
