package com.noob.base.recursion;

/**
 * @ClassName Huh-x
 * @Description TODO
 * @Author Huh-x
 * @Date 2024 2024/4/29 8:48
 */
public class RecursionExample02 {


    public static void main(String[] args) {
        RecursionExample02 re = new RecursionExample02();
        for (int i = 1; i <= 10; i++) {
            System.out.println("第"+i+"天剩余桃子数："+re.peach(i));
        }
    }

    /**
     * 猴子吃桃问题：
     * 一堆桃子，猴子第一天吃了其中的一半并且再吃一个，之后的每天猴子都吃剩下的一半然后多吃一个
     * 当吃到第10天却发现只有1个桃子，问最初有多少个桃子
     * 解题思路：逆推（从第10天开始，逆推前几天的规律）
     * 10:1
     * 9：(1+1)*2 => 4
     * 8：(4+1)*2 => 10
     * 7：(10+1)*2 => 22
     * 6：(22+1)*2 =>46
     * ----- 以此类推
     * 输入参数n为天数，当n为10输出1,且天数必须为证书
     * 前一天的桃子数量 = (后一天桃子数量 + 1) *2
     */

    public static int peach(int n){
        // n为第几天，返回的值是第几天的桃子数量
       if(n>0){
           if(n==10){
               return 1;
           }else{
               return (peach(n+1)+1)*2;
           }
       }else{
           System.out.println("天数必须为正数");
           return -1;
       }
    }
}
