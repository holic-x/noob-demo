package com.noob.base.recursion;

/**
 * @ClassName Huh-x
 * @Description TODO
 * @Author Huh-x
 * @Date 2024 2024/4/29 9:12
 */
public class RecursionExample03 {

    public static void main(String[] args) {


    }

    /**
     * 汉诺塔：
     * 一根柱子从上往下从小到大堆叠64个圆盘，需将其重新摆放在一根柱子上（排序规则和原来一致）
     * 规定：小圆盘不能放在大圆盘上，3个柱子之间一次只能移动一个圆盘
     * 假设每秒移动一次，共需多少时间
     *
     * 首先分析其规律，然后进一步扩展
     * 递归方法定义：hanoi(int n,String src,String buffer,String target)
     * n=1:只有一个盘子只需要移动1次
     *  递归退出条件：最后一个大盘子从src移动到target
     * n=2: 分析n=2时每一步的思路（src：A、buffer：B、target：C）
     *  A =》 C
     *  A =》 B
     *  A =》
     * n=3:
     * todo 汉诺塔思路分析
     */

}
