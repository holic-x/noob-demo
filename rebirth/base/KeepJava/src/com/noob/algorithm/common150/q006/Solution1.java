package com.noob.algorithm.common150.q006;

import java.util.ArrayList;
import java.util.List;

/**
 * 006 Z字形变换
 */
public class Solution1 {
    /**
     *
     */
    public String convert(String s, int numRows) {

        // 定义一个集合分别存储行的数据，通过flag来指定当前行(此处使用StringBuilder便于拼接，也可以用二维数组)
        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            list.add(new StringBuilder());
        }

        int flag = 0; // 记录当前插入行的位置
        // 遍历元素，然后将其插入集合合适的位置
        for (int i = 0; i < s.length(); i++) {
            // 获取当前插入行
            StringBuilder curRow = list.get(flag);
            curRow.append(s.charAt(i)); // 插入元素
            // 如果遇到行的上下边界则需要移动这个游标(todo 此处Z型反转的逻辑需注意处理)
            if (flag == 0) {
                flag++;
            } else if (flag == numRows) {
                flag--;
            }
        }

        // 遍历处理好的元素（按行输出）
        StringBuilder res = new StringBuilder();
        for (StringBuilder sb : list) {
            res.append(sb);
        }
        // 返回结果
        return res.toString();
    }
}
