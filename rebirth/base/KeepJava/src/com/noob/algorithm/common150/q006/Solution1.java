package com.noob.algorithm.common150.q006;

import java.util.ArrayList;
import java.util.List;

/**
 * 006 Z字形变换
 */
public class Solution1 {
    /**
     * Z 字形变换：实际就是将字符串按照顺序存放到一个二维数组中
     * 此处的二维数组：可以用一个List<StringBuffer>来平替，便于操作
     */
    public String convert(String s, int numRows) {

        // 对边界进行处理
        if(numRows < 2) return s;

        // 定义一个集合分别存储行的数据，通过flag来指定当前行(此处使用StringBuilder便于拼接，也可以用二维数组)
        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            list.add(new StringBuilder());
        }

        int idx = 0;// 记录当前插入行的位置
        int flag = -1; // 一个操作标识
        // 遍历元素，然后将其插入集合合适的位置
        for (int i = 0; i < s.length(); i++) {
            // 插入指定元素
            StringBuilder curRow = list.get(idx); // 获取当前插入行
            curRow.append(s.charAt(i)); // 插入元素

            // 如果遇到行的上下边界则需要进行切换(此处Z型反转的逻辑需注意处理)
            if (idx == 0 || idx == numRows - 1) {
                flag = -flag; // 取相反数，表示切换+1、-1操作（遇到上边界则后面的操作是+1、遇到下边界则后面的操作是-1）
            }

            // 更新插入的行位置
            idx = idx + flag;
        }

        // 遍历处理好的元素（按行输出）
        StringBuilder res = new StringBuilder();
        for (StringBuilder sb : list) {
            res.append(sb);
        }
        // 返回结果
        return res.toString();
    }

    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        System.out.println(solution.convert("AB", 1)); // 边界情况校验
        System.out.println(solution.convert("ABCDE", 2));
    }
}
